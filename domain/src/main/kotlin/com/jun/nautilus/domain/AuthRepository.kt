package com.jun.nautilus.domain

interface AuthRepository {
    fun save(authUser: AuthUser)
    fun findByEmail(email: String): AuthUser?

    fun findById(id: String): AuthUser?
}