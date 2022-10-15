package com.jun.nautilus.server.mvc.service

import com.jun.nautilus.auth.*
import com.jun.nautilus.domain.*
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
    lateinit var authenticator: Authenticator

    @MockK
    lateinit var userService: UserService

    @MockK
    lateinit var jwtAuthenticationProvider: JwtAuthenticationProvider

    lateinit var sut: AuthService

    @BeforeEach
    fun setUp() {

        sut = AuthService(authenticator,authManager, userService,jwtAuthenticationProvider)
    }


}