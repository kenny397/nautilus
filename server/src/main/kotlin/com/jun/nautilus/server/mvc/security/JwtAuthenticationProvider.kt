package com.jun.nautilus.server.mvc.security


import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class JwtAuthenticationProvider(private val userDetailsService: UserDetailsService) {

    private var SECRET = "kenny"

    init {
        SECRET = Base64.getEncoder().encodeToString(SECRET.toByteArray())
    }


    fun createAccessToken(userId: String): String {
        val duration: Long = 1
        return Jwts.builder()
            .setSubject("accessToken")
            .claim("userId",userId)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(duration)))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact()
    }


    fun verifyToken(token: String): Boolean {
        return try {
            val claims = getAllClaims(token)
            val expiration = claims.expiration
            println(expiration)
            expiration.after(Date())
        }
        catch (e: ExpiredJwtException) {
            false
        }catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    // Claim 조회
    fun getTokenInfo(token: String, key: String ): Any? {
        val claims: Claims = getAllClaims(token)
        return claims[key]
    }
    fun getAuthentication(userId: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(userId)
        return UsernamePasswordAuthenticationToken(userDetails,null,userDetails.authorities)
    }


    private fun getAllClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token).body


    }
}