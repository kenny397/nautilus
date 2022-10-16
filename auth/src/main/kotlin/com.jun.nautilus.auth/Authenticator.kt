package com.jun.nautilus.auth

interface Authenticator {

    fun authenticate(email: String, password: String): Result

    enum class Result{
        Success,
        Failed
    }
}