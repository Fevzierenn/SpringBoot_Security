package com.springBootSecurity.demoSecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails eren= User.builder().username("eren")
                .password("{noop}test123")
                .roles("ISCI")
                .build();
        UserDetails rihanna= User.builder().username("rihanna")
                .password("{noop}test123")
                .roles("ISCI","MENEJER")
                .build();
        UserDetails messi= User.builder().username("messi")
                .password("{noop}test123")
                .roles("ISCI","MENEJER","ADMIN31")
                .build();
        return new InMemoryUserDetailsManager(eren,rihanna,messi);
    }

    @Bean       //custom login form.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers("/").hasRole("ISCI")
                        .requestMatchers("/leaders/**").hasRole("MENEJER")
                        .requestMatchers("/systems/**").hasRole("ADMIN31")
                        .anyRequest().authenticated())
                .formLogin(form->
                        form.loginPage("/showMyLoginPage")      //controller
                                .loginProcessingUrl("/authenticateTheUser") //spring bunu bizim yerimize halledecek(id password bakacak)
                                .permitAll()
                ).logout(logout->logout.permitAll())
                .exceptionHandling(configurer->
                        configurer.accessDeniedPage("/access-denied"));  //istedigimiz ismi verebiliriz.
        return http.build();
    }

}
