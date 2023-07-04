package com.example.BlogMode.config;

//import com.example.BlogMode.security.JwtAuthenticationEntryPoint;
//import com.example.BlogMode.security.JwtAuthenticationFilter;
import com.example.BlogMode.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String[] PUBLIC_URLS={"/api/auth/login"};

    @Autowired
    private CustomUserDetailService customUserDetailService;

//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    private JwtAuthenticationFilter jwtAuthenticationFilter;

//    public SecurityConfig(CustomUserDetailService customUserDetailService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.customUserDetailService = customUserDetailService;
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                                .authorizeRequests(authorize -> configureAuthorization(authorize))
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .httpBasic(Customizer.withDefaults()) // Use HTTP Basic authentication
                .build();

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .antMatchers(PUBLIC_URLS)
//                .permitAll()
//                .antMatchers(HttpMethod.GET)
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and().exceptionHandling()
//                .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(this.jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
//        DefaultSecurityFilterChain build = http.build();
//        return build;


//                .authorizeRequests(authorize -> authorize
////                        .requestMatchers("/api/auth/login").permitAll()
//                        .anyRequest().authenticated() // Require authentication for other endpoints
//                )
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                )
//                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
//                .sessionManagement(sessionManagement -> sessionManagement
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session management
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .build(); // Use HTTP Basic authentication
    }

    private void configureAuthorization(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorize) {
        authorize
//                .antMatchers("/public/**").permitAll() // Define public endpoints
                .anyRequest()
                .authenticated(); // Require authentication for other endpoints
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configure) throws Exception {
//        return configure.getAuthenticationManager();
//    }



}



//                .authorizeRequests(authorize -> configureAuthorization(authorize))
//                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
//                .httpBasic(Customizer.withDefaults()) // Use HTTP Basic authentication
//                .build();


//    private void configureAuthorization(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorize) {
//        authorize
//                .antMatchers("/public/**").permitAll() // Define public endpoints
//                .anyRequest()
//                .authenticated(); // Require authentication for other endpoints
//    }
