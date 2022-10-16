package com.jun.nautilus.server.mvc.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtAuthenticationResult(private val userDetails: UserDetails, private val jwt: String): Authentication {
    override fun getName(): String = userDetails.username

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()


    override fun getCredentials(): Any = jwt

    override fun getDetails(): Any? =null

    override fun getPrincipal(): Any =userDetails

    override fun isAuthenticated(): Boolean =true

    override fun setAuthenticated(isAuthenticated: Boolean) {
        throw IllegalArgumentException("Do not use this method")
    }
}