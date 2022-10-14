package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AuthManager
import com.jun.nautilus.domain.AuthRepository
import com.jun.nautilus.domain.Authenticator
import com.jun.nautilus.domain.AuthenticatorTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class AuthenticatorImplTest: AuthenticatorTest{

    override lateinit var sut: Authenticator
    override lateinit var authManager: AuthManager

    lateinit var authRepository: AuthRepository

    @BeforeEach
    fun setUp(){
        authRepository=AuthRepositoryImpl()
        authManager= AuthManagerImpl(authRepository,Sha256Encoder())
        sut= AuthenticatorImpl(authRepository,Sha256Encoder())

    }
}