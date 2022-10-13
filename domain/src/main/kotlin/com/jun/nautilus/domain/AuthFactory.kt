package com.jun.nautilus.domain

import java.math.BigInteger
import java.security.MessageDigest

interface AuthFactory {
    fun create(userId: String, email: String, password: String): AuthUser


}