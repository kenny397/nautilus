package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AuthUser

class AuthUserImpl(override val userId: String,
                   override val email: String,
                   override val password: String) : AuthUser.Base() {
}