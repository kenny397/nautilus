package com.jun.nautilus.server.mvc.security

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationProvider constructor(
    @Autowired
    private val jwtTokenManager: JwtTokenManager,
    @Autowired
    private val userDetailsService: UserDetailsService
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication? {
        if (authentication !is JwtAuthenticationRequest) {
            return null
        }
        try{
            val verification=jwtTokenManager.verifyToken(authentication.jwt)
            if(verification.type==TokenType.Refresh){
                throw BadCredentialsException("잘못된 토큰 정보입니다")
            }
            val userDetails = userDetailsService.loadUserByUsername(verification.userId)
            return JwtAuthenticationResult(userDetails,authentication.jwt)
        }
        catch (e: ExpiredJwtException){
            throw BadCredentialsException("토큰이 만료되었습니다")
        }
        catch (e: InvalidAuthTokenException){
            throw BadCredentialsException("token 이 문제가 있습니다.")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return JwtAuthenticationRequest::class.java.isAssignableFrom(authentication)
    }
}