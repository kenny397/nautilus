package com.jun.nautilus.server.mvc.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtAuthenticationFilter(
    private val jwtAuthenticationProvider: JwtAuthenticationProvider
    )
    : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader: String? = request.getHeader("Authorization") ?: return filterChain.doFilter(request, response)

        val token = authorizationHeader?.substring("Bearer ".length) ?: return filterChain.doFilter(request, response)
        if(jwtAuthenticationProvider.verifyToken(token)){
            val userId = jwtAuthenticationProvider.getTokenInfo(token,"userId") as String

            val authentication: Authentication = jwtAuthenticationProvider.getAuthentication(userId)
            SecurityContextHolder.getContext().authentication = authentication
            filterChain.doFilter(request, response)
        }else{

            response.sendError(HttpStatus.UNAUTHORIZED.value(),"accessToken has been expired!")
        }
    }



}