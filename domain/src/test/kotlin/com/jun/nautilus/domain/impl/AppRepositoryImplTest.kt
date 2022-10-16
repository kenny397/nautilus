package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AppRepositoryTest
import org.junit.jupiter.api.BeforeEach

class AppRepositoryImplTest: AppRepositoryTest {

    override lateinit var sut: AppRepository

    @BeforeEach
    fun setUp(){
        sut = AppRepositoryImpl()
    }


}