package com.jun.nautilus.auth

interface AuthUser {

    val userId: String
    val email: String
    val password: String


    abstract class Base: AuthUser {

        final override fun hashCode(): Int {
            return userId.hashCode()
        }

        final override fun equals(other: Any?): Boolean {
            return other is AuthUser
                    && other.userId == userId
        }

    }
}