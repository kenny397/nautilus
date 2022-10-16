package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.*

class AuthManagerImpl(
    private val authRepository: AuthRepository,
    private val passwordEncoder: PasswordEncoder
): AuthManager {


    override fun create(userId: String, email: String, password: String) {
        val authUser= AuthUserImpl(userId, email, passwordEncoder.encoding(password))
        authRepository.save(authUser)
    }


}