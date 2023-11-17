package chaobei.xyz;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {


    @org.springframework.web.bind.annotation.RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}


