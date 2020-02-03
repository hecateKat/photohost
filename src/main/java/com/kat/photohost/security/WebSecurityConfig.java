package com.kat.photohost.security;

import com.kat.photohost.entity.User;
import com.kat.photohost.impl.UserDetailsServiceImpl;
import com.kat.photohost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsServiceimpl;
    private UserRepository userRepository;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceimpl, UserRepository userRepository) {
        this.userDetailsServiceimpl = userDetailsServiceimpl;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceimpl);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/test1").hasRole("USER")
                .antMatchers("/test2").hasRole("ADMIN")
                .antMatchers("/uploadImage").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
//                .headers().frameOptions().disable()
//                .and()
//                .headers().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void TestUser(){
        User user = new User("Meow", passwordEncoder().encode("meow123"), "ROLE_USER");
        User admin = new User("Kat", passwordEncoder().encode("abc123"), "ROLE_ADMIN");
        userRepository.save(user);
        userRepository.save(admin);
    }


}
