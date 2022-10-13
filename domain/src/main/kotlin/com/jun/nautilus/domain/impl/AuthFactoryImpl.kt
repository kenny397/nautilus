package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AuthFactory
import com.jun.nautilus.domain.AuthUser

class AuthFactoryImpl: AuthFactory {
    override fun create(userId: String, email: String, password: String): AuthUser {
        return AuthUserImpl(userId, email, password)
    }


}