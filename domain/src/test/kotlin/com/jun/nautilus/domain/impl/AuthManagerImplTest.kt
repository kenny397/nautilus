package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.*
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