package com.jun.nautilus.server.mvc.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationRequest(val jwt: String): Authentication {
    override fun getName(): String = ""

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getCredentials(): Any = jwt

    override fun getDetails(): Any? = null

    override fun getPrincipal(): Any = jwt

    override fun isAuthenticated(): Boolean = false

    override fun setAuthenticated(isAuthenticated: Boolean) {
        throw IllegalArgumentException("Do not use this method")
    }


}