package com.hcc.config;

import com.hcc.filters.JwtFilter;
import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    JwtFilter jwtFilt;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailServiceImpl).passwordEncoder(customPasswordEncoder.getPasswordEncoder());
    }
    @Override

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling().authenticationEntryPoint((request, response, exception) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
        });

        http.authorizeRequests()
//                // GET Requests
//                .antMatchers(HttpMethod.GET, "/",
//                            "/api/auth/login", "/api/auth/validate",
//                            "/api/assignments", "/api/assignments/{id}")
//                .permitAll()
//                // POST Requests
//                .antMatchers(HttpMethod.POST,
//                "/api/assignments")
//                .permitAll()
//                // PUT Requests
//                .antMatchers(HttpMethod.PUT,
//                "/api/assignments/{id}")
//                .permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFilt, UsernamePasswordAuthenticationFilter.class);
    }
}
