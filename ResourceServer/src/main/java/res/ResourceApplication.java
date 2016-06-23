package res;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by i855631 on 6/21/16.
 */

@SpringBootApplication
@RestController
public class ResourceApplication extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.authorizeRequests().anyRequest().authenticated();
    }

    @RequestMapping("/")
        public Message res(){
        return new Message("Hello World");
    }

    @Bean
    HeaderHttpSessionStrategy sessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    class Message {
        String id = UUID.randomUUID().toString();
        String content;
        public Message(){
            content = null;
        }
        public Message(String content){
            this.content = content;
        }
        public String getId(){
            return id;
        }
        public String getContent(){
            return content;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
}
