package com.jun.nautilus.auth

interface AuthRepository {
    fun save(authUser: AuthUser)
    fun findByEmail(email: String): AuthUser?

    fun findById(id: String): AuthUser?
}