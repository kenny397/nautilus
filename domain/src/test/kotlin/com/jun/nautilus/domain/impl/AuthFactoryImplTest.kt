package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AuthFactory
import com.jun.nautilus.domain.AuthFactoryTest
import org.junit.jupiter.api.BeforeEach

class AuthFactoryImplTest: AuthFactoryTest {

    override lateinit var sut: AuthFactory

    @BeforeEach
    fun setUp(){
        sut = AuthFactoryImpl()
    }

}