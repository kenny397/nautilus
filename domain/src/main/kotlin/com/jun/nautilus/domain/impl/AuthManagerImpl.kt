package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.*
import java.security.MessageDigest

class AuthManagerImpl(
    private val authFactory: AuthFactory,
    private val authRepository: AuthRepository,
    private val passwordEncoder: PasswordEncoder
): AuthManager {


    override fun login(email: String, password: String): Boolean {
        return authRepository.findByEmail(email)
            ?.let{
                it.password== passwordEncoder.encoding(password)
            }?:throw IllegalArgumentException("잘못된 회원이메일 입니다")
    }

    override fun register(userId: String, email: String, password: String): AuthUser {
        val authUser=authFactory.create(userId, email, passwordEncoder.encoding(password))
        return authRepository.save(authUser)
    }

}