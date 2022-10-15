package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.*
import org.junit.jupiter.api.BeforeEach

class AuthManagerImplTest: AuthManagerTest {

    override lateinit var sut: AuthManager
    override lateinit var authenticator: Authenticator


    @BeforeEach
    fun setUp(){
        val authRepository = AuthRepositoryImpl()
        sut = AuthManagerImpl(authRepository,Sha256Encoder())
        authenticator = AuthenticatorImpl(authRepository,Sha256Encoder())
    }
}