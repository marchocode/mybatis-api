package xyz.chaobei.config;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.chaobei.MybatisApiContext;
import xyz.chaobei.handler.MybatisHandlerAdapter;
import xyz.chaobei.handler.MybatisHandlerMapping;

@Configuration
public class MybatisApiAutoConfigure {

    private Logger logger = LoggerFactory.getLogger(MybatisApiAutoConfigure.class);

    @Bean
    @ConditionalOnBean(SqlSession.class)
    public MybatisApiContext mybatisApiContext(SqlSession sqlSession) {
        logger.info("mybatisApiContext");
        return new MybatisApiContext(sqlSession);
    }

    @Bean
    public MybatisHandlerMapping mybatisHandlerMapping(MybatisApiContext mybatisApiContext) {
        return new MybatisHandlerMapping(mybatisApiContext);
    }

    @Bean
    public MybatisHandlerAdapter mybatisHandlerAdapter(MybatisApiContext mybatisApiContext) {
        return new MybatisHandlerAdapter(mybatisApiContext);
    }

}
