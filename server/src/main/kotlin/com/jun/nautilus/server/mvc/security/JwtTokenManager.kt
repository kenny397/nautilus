package com.jun.nautilus.server.mvc.security


import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtTokenManager {

    private var SECRET = "kenny"

    init {
        SECRET = Base64.getEncoder().encodeToString(SECRET.toByteArray())
    }


    fun createAccessToken(userId: String, duration: Long=60*60*5): String {

        return Jwts.builder()
            .claim("type","access")
            .claim("userId",userId)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(duration)))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact()
    }

    fun createRefreshToken(userId: String, duration: Long=60*60*24): String{
        return Jwts.builder()
            .claim("type","refresh")
            .claim("userId",userId)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(duration)))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact()
    }


    fun verifyToken(token: String): JwtTokenVerification{
        try{
            val claims = getParseClaims(token)
            return JwtTokenVerification(
                userId = claims["userId"].toString(),
                type = if(claims["type"].toString()=="refresh"){
                            TokenType.Refresh
                        }else{
                            TokenType.Access
                             },
            )
        }catch (e: ExpiredJwtException){
            throw e
        }catch (e: JwtException){
            throw InvalidAuthTokenException("잘못된 토큰입니다",e)
        }catch (e: RuntimeException){
            throw InvalidAuthTokenException("잘못된 토큰입니다",e)
        }

    }

    private fun getParseClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token).body
    }

}







data class JwtTokenVerification(
    val userId: String,
    val type: TokenType,
    )


enum class TokenType {
    Refresh,
    Access
}

class InvalidAuthTokenException(message: String, cause: Throwable?) : RuntimeException(message, cause)