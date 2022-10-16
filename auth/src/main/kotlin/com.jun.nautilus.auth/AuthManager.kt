package com.jun.nautilus.auth

/**
 * 인증관련 manager
 *
 */
interface AuthManager {
    fun create(userId: String, email: String, password: String)
}