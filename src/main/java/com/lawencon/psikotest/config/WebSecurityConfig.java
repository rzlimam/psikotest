package com.lawencon.psikotest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lawencon.psikotest.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPointBean() throws Exception{
        return new JwtAuthenticationEntryPoint();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()

                .authorizeRequests().antMatchers("/login","/register",
                		"/user", "/user/*", "/user/*/*", 
                		"/profile", "/profile/*", "/profile/*/*",
                		"/questiontype","/questiontype/*","questiontype/*/*",
                		"/question", "/question/*", "/question/*/*",
                		"/packages", "/packages/*", "/packages/*/*", 
                		"/packagedetail", "/packagedetail/*", "/packagedetail/*/*",
                		"/haa", "/haa/*", "/haa/*/*",
                		"/daa", "/daa/*", "/daa/*/*",
                		"/questionassign","/questionassign/*","/questionassign/*/*",
//                		"/report/*/*", 
                		"/download/*", "/download/*/*",
                		"/dashboard", "/dashboard/*").permitAll().

        anyRequest().authenticated().and().

        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()

                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

}
