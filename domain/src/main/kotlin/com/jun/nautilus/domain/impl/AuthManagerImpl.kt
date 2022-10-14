package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.*
import java.security.MessageDigest

class AuthManagerImpl(
    private val authRepository: AuthRepository,
    private val passwordEncoder: PasswordEncoder
): AuthManager {



    override fun create(userId: String, email: String, password: String) {
        val authUser=AuthUserImpl(userId, email, passwordEncoder.encoding(password))
        authRepository.save(authUser)
    }

}