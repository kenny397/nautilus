package com.jun.nautilus.server.mvc.security

import com.jun.nautilus.auth.impl.AuthRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val authRepository: AuthRepository): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val authUser = authRepository.findById(username!!) ?: throw UsernameNotFoundException("존재하지 않는 email입니다")

        return UserDetailsImpl(authUser)
    }
}