package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.AuthRepositoryTest
import org.junit.jupiter.api.BeforeEach

class AuthRepositoryImplTest: AuthRepositoryTest {
    override lateinit var sut: AuthRepository

    @BeforeEach
    fun setUp(){
        sut = AuthRepositoryImpl()
    }
}