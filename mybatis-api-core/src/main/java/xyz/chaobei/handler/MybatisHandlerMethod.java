package xyz.chaobei.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.chaobei.MybatisApiContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class MybatisHandlerMethod  {

    private final MybatisApiContext context;
    private final Method targetMethod;
    private final Class<?> targetClass;
    private final Logger logger = LoggerFactory.getLogger(MybatisHandlerMethod.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MybatisHandlerMethod(MybatisApiContext context, Method targetMethod, Class<?> targetClass) {
        this.context = context;
        this.targetMethod = targetMethod;
        this.targetClass = targetClass;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        SqlSession sqlSession = context.getSqlSession();
        Object mapper = sqlSession.getMapper(targetClass);

        Object data = targetMethod.invoke(mapper);
        // write to response.
        logger.info("receive data = {}", data);

        byte[] bytes =  objectMapper.writeValueAsBytes(data);

        response.getOutputStream().write(bytes);
        response.addHeader("Content-Type","application/json");
        response.setContentLength(bytes.length);
    }
}
