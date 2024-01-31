package com.example.bingobackend.provider

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${app.jwtSecret}") private val jwtSecret: String,
) {

    fun generateToken(id: UUID): String {
        val now = Date()
        val expiryDate = Date.from(Instant.now().plusMillis(Duration.ofDays(14).toMillis()))
        println(jwtSecret.toByteArray().size)
        val signingKey = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
//        val signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)

        return Jwts.builder().setIssuer(id.toString()).setIssuedAt(now).setExpiration(expiryDate)
//            .signWith(signingKey)
            .signWith(signingKey, SignatureAlgorithm.HS512).compact()
    }

    fun validateToken(token: String): Boolean {
        return (!isTokenExpired(token))
    }

    fun extractId(token: String): UUID {
        return UUID.fromString(
            Jwts.parserBuilder().setSigningKey(jwtSecret.toByteArray()).build().parseClaimsJws(token).body.issuer
        )
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration =
            Jwts.parserBuilder().setSigningKey(jwtSecret.toByteArray()).build().parseClaimsJws(token).body.expiration
        return expiration.before(Date())
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.cookies[0]
        return bearerToken.value
//        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            bearerToken.substring(7)
//        } else null
    }
}
