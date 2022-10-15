package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.AuthUser

class AuthUserImpl(override val userId: String,
                   override val email: String,
                   override val password: String) : AuthUser.Base() {
}