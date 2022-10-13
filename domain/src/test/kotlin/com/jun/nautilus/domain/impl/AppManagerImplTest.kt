package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AppManager
import com.jun.nautilus.domain.AppManagerTest
import org.junit.jupiter.api.BeforeEach

class AppManagerImplTest: AppManagerTest {


    override lateinit var sut: AppManager

    @BeforeEach
    fun setUp(){
        sut= AppManagerImpl(UUIDGenerator(), AppRepositoryImpl())
    }
}