package chaobei.xyz;

import chaobei.xyz.config.MyHandlerAdapter;
import chaobei.xyz.config.MyHandlerMapping;
import chaobei.xyz.mapper.TestMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Resource
    TestMapper testMapper;

    @Resource
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        DispatcherServlet dispatcherServlet = applicationContext.getBean(DispatcherServlet.class);
        System.out.println(dispatcherServlet.getHandlerMappings());
        System.out.println(testMapper.list());
    }
}