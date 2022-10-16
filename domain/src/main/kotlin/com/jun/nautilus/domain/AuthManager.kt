package com.jun.nautilus.domain

/**
 * 인증관련 manager
 * 로그인, 회원가입 기능을 담당
 */
interface AuthManager {
    fun login(email: String, password: String): Boolean
    fun register(userId: String, email: String, password: String): AuthUser
}