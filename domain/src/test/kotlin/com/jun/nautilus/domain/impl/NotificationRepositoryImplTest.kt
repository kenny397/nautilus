package com.jun.nautilus.domain.impl


import com.jun.nautilus.domain.NotificationRepositoryTest
import org.junit.jupiter.api.BeforeEach

class NotificationRepositoryImplTest: NotificationRepositoryTest {
    override lateinit var sut: NotificationRepository

    @BeforeEach
    fun setUp(){
        sut = NotificationRepositoryImpl()
    }

}