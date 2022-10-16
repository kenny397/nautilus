package com.jun.nautilus.server.mvc.service


import com.jun.nautilus.auth.AuthManager
import com.jun.nautilus.auth.Authenticator
import com.jun.nautilus.domain.UserService
import com.jun.nautilus.server.mvc.controller.LoginRequest
import com.jun.nautilus.server.mvc.security.JwtTokenManager
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
    lateinit var jwtAuthenticationProvider: JwtTokenManager

    lateinit var sut: AuthService

    @BeforeEach
    fun setUp() {

        sut = AuthService(authManager,authenticator, userService,jwtAuthenticationProvider)
    }

    @Test
    fun `아이디 비밀번호가 틀리면 IllegalArgumentException 예외를 던진다`() {
        //given
        val loginRequest= LoginRequest("test@test.com","test")

        every { authenticator.authenticate(loginRequest.email,loginRequest.password) }returns Authenticator.Result.Failed

        //when
        assertThrows<IllegalArgumentException> { sut.login(loginRequest) }
        //then
    }


}