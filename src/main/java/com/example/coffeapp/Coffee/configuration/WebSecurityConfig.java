package com.example.coffeapp.Coffee.configuration;

import com.example.coffeapp.Coffee.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                        .antMatchers("/", "/registration").permitAll()
                        .antMatchers("/statistics/**").hasAnyRole("DIRECTOR","ADMIN")
                        .antMatchers("/menu/**").hasAnyRole("DIRECTOR","ADMIN")
                        .antMatchers("/location/**").hasAnyRole("DIRECTOR","ADMIN")
                        .antMatchers("/page/**").hasAnyRole("DIRECTOR","ADMIN")
                        .antMatchers("/user/**").hasAnyRole("DIRECTOR","ADMIN")
                        .antMatchers("/order/**").hasAnyRole("DIRECTOR", "SELLER")
                        .anyRequest()
                        .authenticated()
                .and()
                        .formLogin()
//                       .loginPage("/login")
                        .defaultSuccessUrl("/hello")
                        .permitAll()
                .and()
                        .logout()
                        .permitAll();
    }








    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsServiceImpl);

//        auth.jdbcAuthentication().dataSource(dataSource);

//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .usersByUsernameQuery("select user_name, password, active from user where user_name=?")
//                .authoritiesByUsernameQuery("select u.user_name, ur.roles from user u join user_role ur on u.id = ur.user_id where u.user_name=?");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    //    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout((logout) -> logout.permitAll());
//
//        return http.build();
//    }
}
