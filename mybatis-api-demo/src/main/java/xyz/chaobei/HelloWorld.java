package xyz.chaobei;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chaobei.mapper.TestMapper;

import javax.annotation.Resource;

@RestController
public class HelloWorld {

    @Resource
    private TestMapper testMapper;

    @GetMapping("/hello")
    public Object hello() {
        return testMapper.list();
    }

}


