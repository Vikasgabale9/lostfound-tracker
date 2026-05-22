package com.vikas.lostfound.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

            // Disable CSRF only for H2 console
            .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/h2-console/**")
            )

            // Authorization rules
            .authorizeHttpRequests(auth -> auth

                    .requestMatchers(
                            "/auth/register",
                            "/auth/login",
                            "/h2-console/**",
                            "/swagger-ui/**",
                            "/v3/api-docs/**"
                    ).permitAll()

                    .anyRequest()
                    .authenticated()
            )

            // Allow H2 console frames
            .headers(headers -> headers
                    .frameOptions(frame -> frame.sameOrigin())
            )

            // Enable basic authentication
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            UserDetailsService uds,
            PasswordEncoder pe) {

        DaoAuthenticationProvider dap =
                new DaoAuthenticationProvider(uds);

        dap.setPasswordEncoder(pe);

        return new ProviderManager(dap);
    }
}