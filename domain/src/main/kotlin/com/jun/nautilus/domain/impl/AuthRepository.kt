package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AuthUser

interface AuthRepository {
    fun save(authUser: AuthUser): AuthUser
    fun findByEmail(email: String): AuthUser?

    fun findById(id: String): AuthUser?
}