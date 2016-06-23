package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by i855631 on 6/9/16.
 */
@SpringBootApplication
@RestController
@EnableZuulProxy
public class UIApplication {

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/index.html", "/views/home.html", "/views/login.html","/", "/login").permitAll()
                    .anyRequest().authenticated()
                    .and().logout().logoutSuccessUrl("/")
                    .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                    .csrf().csrfTokenRepository(csrfTokenRepository());
        }
        //because angular expects the csrf token to be in this format
        private CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }

    }

//    @RequestMapping("/login")
//    public

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping("/token")
    @ResponseBody
    public Map<String, String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }

//    @RequestMapping("/resource")
//    public Map<String, Object> res(){
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("id", UUID.randomUUID().toString());
//        model.put("content", new String("Hello World"));
//        return model;
//    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(UIApplication.class, args);
    }
}
