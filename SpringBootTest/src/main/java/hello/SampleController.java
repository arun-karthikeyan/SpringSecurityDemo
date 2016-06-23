package hello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by i855631 on 6/8/16.
 */
@Controller
@EnableAutoConfiguration
public class SampleController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World from Spring Boot MAC IntelliJ IDE!";
    }

    @RequestMapping("/new")
    @ResponseBody
    String newPage() { return "Hi this is a new route !"; }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
