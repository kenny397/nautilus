package com.jun.nautilus.domain



interface AuthFactory {
    fun create(userId: String, email: String, password: String): AuthUser


}