package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.AuthManager
import com.jun.nautilus.auth.AuthManagerTest
import com.jun.nautilus.auth.Authenticator
import org.junit.jupiter.api.BeforeEach

class AuthManagerImplTest: AuthManagerTest {

    override lateinit var sut: AuthManager
    override lateinit var authenticator: Authenticator


    @BeforeEach
    fun setUp(){
        val authRepository= AuthRepositoryImpl()
        sut = AuthManagerImpl(authRepository,Sha256Encoder())
        authenticator = AuthenticatorImpl(authRepository,Sha256Encoder())
    }
}