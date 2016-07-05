package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * Created by i855631 on 6/29/16.
 */
@SpringBootApplication
@EnableZuulProxy
@RestController
public class GatewayAPI {
    public static void main(String[] args){
        SpringApplication.run(GatewayAPI.class, args);
    }

    @RequestMapping("/token")
    @ResponseBody
    public Map<String, String> token(HttpSession session){
        return Collections.singletonMap("token", session.getId());
    }

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
