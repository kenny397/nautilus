package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AuthRepository
import com.jun.nautilus.domain.Authenticator
import com.jun.nautilus.domain.PasswordEncoder

class AuthenticatorImpl(
    private val authRepository: AuthRepository,
    private val passwordEncoder: PasswordEncoder
) : Authenticator {

    override fun authenticate(email: String, password: String): Authenticator.Result {
        return authRepository.findByEmail(email)
                    ?.run{
                        if(this.password == passwordEncoder.encoding(password))Authenticator.Result.Success
                            else Authenticator.Result.Failed
                    }?:throw IllegalArgumentException("잘못된 회원이메일 입니다")
    }
}