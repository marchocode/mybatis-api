package xyz.chaobei.handler;

import org.apache.ibatis.mapping.MappedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import xyz.chaobei.MybatisApiContext;
import xyz.chaobei.annotation.MybatisRequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MybatisHandlerMapping extends AbstractHandlerMapping implements InitializingBean {

    private final MybatisApiContext context;
    private final Logger logger = LoggerFactory.getLogger(MybatisHandlerMapping.class);
    private final Pattern pattern = Pattern.compile("^(\\S+)\\.(\\S+)$");
    private final Map<String, Object> requests = new ConcurrentHashMap<>();

    public MybatisHandlerMapping(MybatisApiContext context) {
        this.context = context;
    }

    private void init() throws ClassNotFoundException {

        Collection<MappedStatement> mappedStatements = context.getMappedStatements();

        for (MappedStatement mp : mappedStatements) {

            Matcher matcher = pattern.matcher(mp.getId());
            if (!matcher.find()) {
                continue;
            }

            logger.info("match class {}, method is {}", matcher.group(1), matcher.group(2));
            Class<?> cls = Class.forName(matcher.group(1));

            MybatisRequestMapping classMapping = cls.getAnnotation(MybatisRequestMapping.class);

            if (Objects.isNull(classMapping)) {
                continue;
            }

            String base = classMapping.value();
            Method[] methods = cls.getMethods();

            for (Method method : methods) {
                MybatisRequestMapping methodMapping = method.getAnnotation(MybatisRequestMapping.class);

                if (Objects.isNull(methodMapping)) {
                    continue;
                }

                String path = methodMapping.value();
                requests.put(base.concat(path), new MybatisHandlerMethod(context, method, cls));
            }

        }
    }

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception {

        logger.info("request URI={}", request.getRequestURI());

        if (requests.isEmpty()) {
            this.init();
        }

        return requests.get(request.getRequestURI());
    }

    @Override
    public void afterPropertiesSet() {
        setOrder(3);
    }
}
