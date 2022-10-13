package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.UserRepository
import com.jun.nautilus.domain.UserRepositoryTest
import org.junit.jupiter.api.BeforeEach

class UserRepositoryImplTest : UserRepositoryTest {

    override lateinit var sut: UserRepository

    @BeforeEach
    fun setUp(){
        sut = UserRepositoryImpl()
    }
}