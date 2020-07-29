package com.khramykh.store.config;

import com.khramykh.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        RequestMatcher csrfRequestMatcher = new RequestMatcher() {

            // Disable CSRF protection on the following urls:
            private AntPathRequestMatcher[] requestMatchers = {
                    new AntPathRequestMatcher("/login"),
                    new AntPathRequestMatcher("/logout"),
                    new AntPathRequestMatcher("/api/**")
            };


            @Override
            public boolean matches(HttpServletRequest httpServletRequest) {
                // If the request match one url the CSFR protection will be disabled
                for (AntPathRequestMatcher rm : requestMatchers) {
                    if (rm.matches(httpServletRequest)) {
                        return false;
                    }
                }
                return true;
            }

        };

        http
                .csrf()
                .requireCsrfProtectionMatcher(csrfRequestMatcher)
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/api/**", "/js/**", "/error**", "/login**", "/", "/home", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();

//     need configure this method like this:
//
//        http
//                //HTTP Basic authentication
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/books/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
//                .and()
//                .csrf().disable()
//                .formLogin().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}
