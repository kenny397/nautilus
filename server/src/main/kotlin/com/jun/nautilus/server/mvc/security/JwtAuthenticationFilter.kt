package com.jun.nautilus.server.mvc.security


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationFilter constructor(
    @Autowired
    private val authenticationManager: AuthenticationManager
    )
    : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader= request.getHeader("Authorization") ?: return filterChain.doFilter(request, response)
        // "Authrozation: 123456 {token} "
        if(!authorizationHeader.startsWith("Bearer")) return filterChain.doFilter(request, response)
        val token = authorizationHeader.substring("Bearer ".length)
        try{
            val authenticationRequest = JwtAuthenticationRequest(token)
            val authResult = authenticationManager.authenticate(authenticationRequest)
            if(authResult.isAuthenticated){
                SecurityContextHolder.getContext().authentication = authResult
                filterChain.doFilter(request, response)
            }
        }catch (e: AuthenticationException){
            
            response.sendError(HttpStatus.UNAUTHORIZED.value(), e.message)
        }

    }



}