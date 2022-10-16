package com.jun.nautilus.domain

interface PasswordEncoder {

    fun encoding(password: String): String
}