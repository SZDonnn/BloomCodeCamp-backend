package com.hcc.config;

import com.hcc.filters.JwtFilter;
import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
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
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        http = http.exceptionHandling().authenticationEntryPoint((request, response, exception) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
                }).and();
        http.authorizeRequests()
                // Login "/api/auth/login" ENDPOINT
                // Validate token "/api/auth/validate"
                .antMatchers(HttpMethod.POST,
                        "/api/auth/login",
                                    "/api/auth/validate")
                    .permitAll() // Allow access to all other endpoints
                // Post Assignment "/api/assignments"
                .antMatchers(HttpMethod.POST,
                        "/api/assignments")
                    .authenticated() // Secure specific endpoints
                // Get Assignments By User "/api/assignments"
                // Get Assignment By Id "/api/assignments/{id}"
                .antMatchers(HttpMethod.GET,
                        "/api/assignments",
                        "/api/assignments/{id}")
                .authenticated() // Secure specific endpoints
                // Put Assignment by Id "/api/assignments/{id}"
                .antMatchers(HttpMethod.PUT,
                        "/api/assignments/{id}")
                .authenticated() // Secure specific endpoints
//            .and()
//                .formLogin()
//                .loginPage("/api/auth/login")
//                .defaultSuccessUrl("/dashboard")
//            .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
            .and()
                .addFilterBefore(jwtFilt, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
