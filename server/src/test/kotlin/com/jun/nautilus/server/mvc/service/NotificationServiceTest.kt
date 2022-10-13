package com.jun.nautilus.server.mvc.service

import com.jun.nautilus.domain.AppManager
import com.jun.nautilus.domain.NotificationManager
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class NotificationServiceTest {

    @MockK
    lateinit var notificationManager: NotificationManager

    @MockK
    lateinit var appManager: AppManager


    lateinit var sut: NotificationService

    @BeforeEach
    fun setUp() {

        sut = NotificationService(notificationManager, appManager)
    }

    @Test
    fun `test`() {
        //given

        //when

        //then
    }
}