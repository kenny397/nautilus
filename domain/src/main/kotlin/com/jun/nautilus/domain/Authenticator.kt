package com.jun.nautilus.domain

interface Authenticator {

    fun authenticate(email: String, password: String): Result

    enum class Result{
        Success,
        Failed
    }
}