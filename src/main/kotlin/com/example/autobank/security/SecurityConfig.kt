package com.example.autobank.security




import CookieTokenResolver
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtValidators
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@EnableWebSecurity
@Configuration
class SecurityConfig {

    @Value("\${auth0.audience}")
    private val audience: String = ""

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val issuer: String = ""

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer) as NimbusJwtDecoder
        val withIssuer = JwtValidators.createDefaultWithIssuer(issuer)
        jwtDecoder.setJwtValidator(withIssuer)
        return jwtDecoder
    }

    @Bean
    fun bearerTokenResolver(): BearerTokenResolver {
        return CookieTokenResolver()
    }

    @Bean
    @Override
    fun filterChain(http: HttpSecurity, bearerTokenResolver: BearerTokenResolver): SecurityFilterChain {
        println("YEEEEEEEEEEEEE")
        return http
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers(AntPathRequestMatcher("/api/auth/setuser")).permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer { oauth2 ->
                oauth2
                    .bearerTokenResolver(bearerTokenResolver)
                    .jwt { jwt -> jwt.decoder(jwtDecoder()) }
            }
            .csrf { csrf -> csrf.disable() }
            .build()
    }
}
