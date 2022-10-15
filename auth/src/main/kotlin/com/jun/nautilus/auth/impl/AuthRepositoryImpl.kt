package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.AuthRepository
import com.jun.nautilus.auth.AuthUser

class AuthRepositoryImpl: AuthRepository {

    private val authUsers: MutableList<AuthUser> = mutableListOf()

    override fun save(authUser: AuthUser) {
        authUsers.add(authUser)
    }

    override fun findByEmail(email: String): AuthUser? {
        return authUsers.find { it.email == email }
    }

    override fun findById(id: String): AuthUser? {
        return authUsers.find { it.userId == id }
    }
}