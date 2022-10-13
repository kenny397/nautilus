package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AuthRepository
import com.jun.nautilus.domain.AuthUser

class AuthRepositoryImpl: AuthRepository {

    private val authUsers: MutableList<AuthUser> = mutableListOf()

    override fun save(authUser: AuthUser): AuthUser {
        authUsers.add(authUser)
        return authUser
    }

    override fun findByEmail(email: String): AuthUser? {
        return authUsers.find { it.email == email }
    }

    override fun findById(id: String): AuthUser? {
        return authUsers.find { it.userId == id }
    }
}