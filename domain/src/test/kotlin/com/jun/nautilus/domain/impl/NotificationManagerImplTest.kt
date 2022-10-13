package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.AppRepository
import com.jun.nautilus.domain.NotificationManager
import com.jun.nautilus.domain.NotificationManagerTest
import org.junit.jupiter.api.BeforeEach

class NotificationManagerImplTest: NotificationManagerTest {

    override lateinit var sut: NotificationManager
    override lateinit var appRepository: AppRepository



    @BeforeEach
    fun setUp(){
        appRepository = AppRepositoryImpl()
        sut = NotificationManagerImpl(UUIDGenerator(), NotificationRepositoryImpl())
    }

}