package xyz.chaobei.handler;

import org.apache.ibatis.mapping.MappedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import xyz.chaobei.MybatisApiContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MybatisHandlerMapping extends AbstractHandlerMapping implements InitializingBean {

    private final MybatisApiContext context;
    private final Logger logger = LoggerFactory.getLogger(MybatisHandlerMapping.class);
    private final Pattern pattern = Pattern.compile("^(\\S+)\\.(\\S+)$");

    public MybatisHandlerMapping(MybatisApiContext context) {
        this.context = context;
    }

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception {

        logger.info("request URI={}",request.getRequestURI());
        Collection<MappedStatement> mappedStatements = context.getMappedStatements();

        for (MappedStatement mp : mappedStatements) {

            Matcher matcher = pattern.matcher(mp.getId());
            if (!matcher.find()) {
                continue;
            }
            logger.info("match class {}, method is {}",matcher.group(1),matcher.group(2));
            Class.forName(matcher.group(1));
        }

        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setOrder(3);
    }
}
