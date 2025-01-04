package com.service.medicine.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final String[] PUBLIC_ENDPOINTS_POST = {
        "/users", "/auth/login", "/roles", "/auth/logout", "/auth/introspect", "auth/refresh"
    };
    private static final String[] PUBLIC_ENDPOINTS_GET = {"/products", "/payment/vn-pay-callback"};

    @Value("${jwt.signerKey}")
    private String singerKey;

    private final CustomJwtDecoder customJwtDecoder;

    public SecurityConfig(CustomJwtDecoder customJwtDecoder) {
        this.customJwtDecoder = customJwtDecoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS_POST)
                .permitAll() // cho phép truy cập
                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_GET)
                .permitAll()
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**")
                .permitAll()
                //                        .hasAuthority("SCOPE_ADMIN")//chỉ token chua admin mới truy cập vào point
                // get-users
                //                        .hasRole(Role.ADMIN.toString())
                .anyRequest()
                .authenticated()); // còn lại phải xác thực
        // cung cấp một token vào header authentication trong postman
        httpSecurity.oauth2ResourceServer(
                oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(customJwtDecoder))
                // xử lý lỗi ko xác thực được token gửi vào authentication trong postman
                //                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                );

        httpSecurity.csrf(AbstractHttpConfigurer::disable); // bảo vệ các point trc ...

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
