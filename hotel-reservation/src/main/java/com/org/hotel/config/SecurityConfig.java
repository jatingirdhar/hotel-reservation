package com.org.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider,
            CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                .anyRequest().authenticated().and().httpBasic().and().sessionManagement().disable().exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return (request, response, authException) -> {
            // Handle unauthorized response here
            customAuthenticationFailureHandler.onAuthenticationFailure(request, response, authException);
        };
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
