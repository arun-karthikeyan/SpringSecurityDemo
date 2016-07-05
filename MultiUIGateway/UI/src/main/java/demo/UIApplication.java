package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.sun.tools.classfile.Attribute.Exceptions;

/**
 * Created by i855631 on 6/29/16.
 */
@SpringBootApplication
@RestController
public class UIApplication extends WebSecurityConfigurerAdapter {
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/","/index.html","/views/home.html")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    public static void main(String[] args){
        SpringApplication.run(UIApplication.class, args);
    }
}
