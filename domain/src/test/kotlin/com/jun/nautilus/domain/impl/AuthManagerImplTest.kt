package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AuthManager
import com.jun.nautilus.domain.AuthManagerTest
import com.jun.nautilus.domain.PasswordEncoder
import org.junit.jupiter.api.BeforeEach

class AuthManagerImplTest: AuthManagerTest {

    override lateinit var sut: AuthManager


    @BeforeEach
    fun setUp(){
        sut = AuthManagerImpl(AuthFactoryImpl(),AuthRepositoryImpl(),Sha256Encoder())

    }
}