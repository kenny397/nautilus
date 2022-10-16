package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AuthRepositoryTest
import org.junit.jupiter.api.BeforeEach

class AuthRepositoryImplTest: AuthRepositoryTest {
    override lateinit var sut: AuthRepository

    @BeforeEach
    fun setUp(){
        sut = AuthRepositoryImpl()
    }
}