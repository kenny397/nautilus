package com.jun.nautilus.server.mvc.security

import com.jun.nautilus.auth.AuthUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val authUser: AuthUser): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList()
    }

    override fun getPassword(): String? = null

    override fun getUsername(): String = authUser.userId

    override fun isAccountNonExpired(): Boolean = true


    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}