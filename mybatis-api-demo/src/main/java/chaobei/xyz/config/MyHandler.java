package chaobei.xyz.config;


import chaobei.xyz.HelloWorld;
import chaobei.xyz.mapper.TestMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

@Component
public class MyHandler implements InvocationHandler {

    @Resource
    private SqlSessionTemplate sqlSession;

    public MyHandler() {

    }

    public Object handler() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var mapper = sqlSession.getMapper(TestMapper.class);

        Method method = TestMapper.class.getMethod("list");
        return method.invoke(mapper,null);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {



        return null;
    }
}
