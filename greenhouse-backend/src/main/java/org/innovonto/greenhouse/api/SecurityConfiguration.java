package org.innovonto.greenhouse.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //According to https://www.baeldung.com/spring-security-session :
    //This is essential to make sure that the Spring Security session registry is notified when the session is destroyed.
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Which requests are available without authorization?
        http
                //TODO implement csrf, see
                //https://spring.io/guides/tutorials/spring-security-and-angular-js/
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();

        /*
            http.sessionManagement()
        .expiredUrl("/sessionExpired.html")
        .invalidSessionUrl("/invalidSession.html");
         */
    }
}
