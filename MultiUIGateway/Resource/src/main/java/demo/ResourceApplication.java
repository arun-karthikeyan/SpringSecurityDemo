package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsUtils;

/**
 * Created by i855631 on 6/29/16.
 */
@SpringBootApplication
@RestController
public class ResourceApplication extends WebSecurityConfigurerAdapter{
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .requestMatchers(CorsUtils::isCorsRequest).permitAll()
                .anyRequest().authenticated();
    }

    @RequestMapping("/")
    public Message home(){
        return new Message("Hello from Resource Server");
    }

    @Bean
    HeaderHttpSessionStrategy sessionStrategy(){
        return new HeaderHttpSessionStrategy();
    }

    public static void main(String[] args){

        SpringApplication.run(ResourceApplication.class, args);

    }
}
