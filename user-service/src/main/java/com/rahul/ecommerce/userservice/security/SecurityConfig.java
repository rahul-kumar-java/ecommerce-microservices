package com.rahul.ecommerce.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Autowired
//	private JwtAuthenticationFilter jwtAuthenticationFilter;
	 
	@Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                    .requestMatchers("/api/auth/login").permitAll()

                    .requestMatchers("/api/users/**").permitAll()

                    .anyRequest().authenticated()
            )

            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    ));
                    
                    
          //  .addFilterBefore(
          //          jwtAuthenticationFilter,
           //         UsernamePasswordAuthenticationFilter.class
           // );

        return http.build();
    }
	
	    // Password encoder bean
	   @Bean
	   PasswordEncoder passwordEncoder() {

	        return new BCryptPasswordEncoder();
	    }
	   
/*	@Bean
	PasswordEncoder passwordEncoder()
	{
	    return NoOpPasswordEncoder.getInstance();
	}  */
	   @Bean
	   AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	       return config.getAuthenticationManager();
	   }

}



















