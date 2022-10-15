package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.AuthRepository
import com.jun.nautilus.auth.Authenticator
import com.jun.nautilus.auth.PasswordEncoder

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