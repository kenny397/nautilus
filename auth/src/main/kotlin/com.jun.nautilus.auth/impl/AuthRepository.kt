package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.AuthUser

interface AuthRepository {
    fun save(authUser: AuthUser)
    fun findByEmail(email: String): AuthUser?

    fun findById(id: String): AuthUser?
}