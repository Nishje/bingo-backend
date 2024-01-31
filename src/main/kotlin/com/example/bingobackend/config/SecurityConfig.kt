package com.example.bingobackend.config

import com.example.bingobackend.provider.JwtTokenProvider
import com.nimbusds.jose.jwk.JWKSet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.web.SecurityFilterChain
import java.net.URI
import java.security.interfaces.RSAPublicKey
import java.time.Duration

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.csrf { csrfConfig: CsrfConfigurer<HttpSecurity> ->
            csrfConfig.disable()
        }
//        httpSecurity
//            .authorizeHttpRequests {
//                it
//                    .requestMatchers("/login").anonymous()
//                    .anyRequest().authenticated()
//            }
////            .oauth2ResourceServer { it.jwt { } }
//        val jwtTokenProvider = JwtTokenProvider("secret")
//        httpSecurity.with(JwtConfig(jwtTokenProvider)) { }
        return httpSecurity
            .build()
    }

//    @Bean
//    fun jwtDecoder(): JwtDecoder {
//        return NimbusJwtDecoder.withPublicKey(publicKey()).build()
//    }
//
//    private fun publicKey(): RSAPublicKey {
//
//        val jwkSet = JWKSet.load(URI.create("https://dev-wtq5hhur26d86qu5.us.auth0.com").toURL())
//
//        return jwkSet.keys.first() as RSAPublicKey
//    }
}
