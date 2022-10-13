package com.jun.nautilus.server.mvc.service

import com.jun.nautilus.domain.AppManager
import com.jun.nautilus.domain.AuthManager
import com.jun.nautilus.domain.NotificationManager
import com.jun.nautilus.domain.UserService
import com.jun.nautilus.server.jpa.repository.OwnerEntityRepository
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class AppServiceTest{

    @MockK
    lateinit var appManager: AppManager

    @MockK
    lateinit var userService: UserService


    lateinit var sut: AppService

    @BeforeEach
    fun setUp() {

        sut = AppService(appManager, userService)
    }
}