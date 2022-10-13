package com.jun.nautilus.server.mvc.service

import com.jun.nautilus.domain.AppManager
import com.jun.nautilus.domain.AuthManager
import com.jun.nautilus.domain.NotificationManager
import com.jun.nautilus.domain.UserService
import com.jun.nautilus.server.mvc.security.JwtAuthenticationProvider
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class AuthServiceTest{

    @MockK
    lateinit var authManager: AuthManager

    @MockK
    lateinit var userService: UserService

    @MockK
    lateinit var jwtAuthenticationProvider: JwtAuthenticationProvider

    lateinit var sut: AuthService

    @BeforeEach
    fun setUp() {

        sut = AuthService(authManager, userService,jwtAuthenticationProvider)
    }


}