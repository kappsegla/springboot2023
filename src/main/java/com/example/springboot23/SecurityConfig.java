package com.example.springboot23;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@EnableMethodSecurity
//@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cities").hasRole("cities")
                        .requestMatchers(HttpMethod.GET, "/api/cities/*").hasAuthority("SCOPE_read")
                        .requestMatchers(HttpMethod.GET,"/api/me").authenticated()
                        .anyRequest().denyAll());
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .oauth2Client(c-> c.oauth2Login()
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/unauthenticated", "/oauth2/**", "/login/**").permitAll()
//                .anyRequest()
//                .fullyAuthenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("http://localhost:8000/realms/external/protocol/openid-connect/logout?redirect_uri=http://localhost:8080/");
//
//        return http.build();
//
//    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder
//                .withJwkSetUri("https://fungover.org/auth/.well-known/jwks.json")
//                .jwsAlgorithm(SignatureAlgorithm.ES256)
//                .build();
//    }

    @Bean
    @Description("In memory Userdetails service registered")
    public UserDetailsService users(PasswordEncoder encoder) {
        // The builder will ensure the passwords are encoded before saving in memory
        UserDetails user = User.builder()
                .username("Stockholm")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("password"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


    //https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-bcrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
