package chaobei.xyz;

import chaobei.xyz.mapper.TestMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Resource
    TestMapper testMapper;

    @Resource
    SqlSessionFactory sqlSessionFactory;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(testMapper.list());
    }
}