package com.fptpoly.main.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().and().formLogin().permitAll();
        http.authorizeRequests().antMatchers("/home","/home/listcar").permitAll()
        .antMatchers("/Admin/**").hasAuthority("ADMIN").antMatchers("/home/member/shopping-cart","/Add","/home/dashboard").hasAuthority("USER")
        .and().exceptionHandling().accessDeniedPage("/403")
        .and().formLogin().loginPage("/home/login").defaultSuccessUrl("/home").permitAll().loginProcessingUrl("/data")
        .usernameParameter("username").passwordParameter("password")
        .and().logout().permitAll().logoutUrl("/login").logoutSuccessUrl("/home");
    }
}
