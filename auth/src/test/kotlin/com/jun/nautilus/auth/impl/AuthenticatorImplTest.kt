package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.AuthManager
import com.jun.nautilus.auth.AuthRepository
import com.jun.nautilus.auth.Authenticator
import com.jun.nautilus.auth.AuthenticatorTest
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