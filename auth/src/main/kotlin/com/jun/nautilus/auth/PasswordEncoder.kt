package com.jun.nautilus.auth

interface PasswordEncoder {

    fun encoding(password: String): String
}