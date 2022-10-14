package com.jun.nautilus.domain

/**
 * 인증관련 manager
 *
 */
interface AuthManager {
    fun create(userId: String, email: String, password: String)
}