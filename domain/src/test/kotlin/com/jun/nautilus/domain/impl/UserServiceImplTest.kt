package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.UserService
import com.jun.nautilus.domain.UserServiceTest
import org.junit.jupiter.api.BeforeEach

class UserServiceImplTest: UserServiceTest {

    override lateinit var sut: UserService

    @BeforeEach
    fun setUp(){
        sut = UserServiceImpl(UUIDGenerator(), UserRepositoryImpl())
    }
}