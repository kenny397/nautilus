package com.jun.nautilus.server.mvc.security

import com.jun.nautilus.server.mvc.controller.LoginRequest
import com.jun.nautilus.server.mvc.controller.RegisterRequest
import com.jun.nautilus.server.mvc.service.AuthService
import org.assertj.core.api.Assertions.*
import org.h2.security.auth.AuthenticationException
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ExtendWith(SpringExtension::class)
@Transactional
internal class JwtAuthenticationProviderTest{

    lateinit var sut: JwtAuthenticationProvider

    @Autowired
    lateinit var jwtTokenManager: JwtTokenManager

    @Autowired
    lateinit var userDetailsService : UserDetailsService

    @Autowired
    lateinit var authService: AuthService

    val registerRequest = RegisterRequest("test","test@ttt.test","asdf")
    val loginRequest = LoginRequest(registerRequest.email,registerRequest.password)

    var testId: String ="";
    @BeforeEach
    fun setUp(){

        authService.register(registerRequest)
        testId=authService.login(loginRequest).userId
        sut = JwtAuthenticationProvider(jwtTokenManager, userDetailsService)
    }

    @Test
    fun `제시된 인증 개체가 jwtToken을 처리할 수 있다`() {
        //given
        val accessToken = jwtTokenManager.createAccessToken(testId)
        val authenticationRequest = JwtAuthenticationRequest(accessToken)

        //when
        val authenticationResult = sut.authenticate(authenticationRequest)

        //then
        assertInstanceOf(JwtAuthenticationResult::class.java,authenticationResult)
        assertThat(authenticationResult!!.isAuthenticated).isTrue
        assertThat(authenticationResult!!.name).isEqualTo(testId)

    }

    @Test
    fun `제시된 인증 개체를 지원하는 경우 true를 반환합니다`() {

        assertThat(sut.supports(JwtAuthenticationRequest::class.java)).isTrue
        assertThat(sut.supports(UsernamePasswordAuthenticationToken::class.java)).isFalse

    }


    //다른 authentication provider가 시도 할 수 있게 null 반환
    @Test
    fun `인증 메서드에서 지원하지 않는 인증객체이면 null을 반환해야 한다`(){
        //given
        val usernamePasswordAuthentication = UsernamePasswordAuthenticationToken("a","a")

        //when
        val authenticationResult=sut.authenticate(usernamePasswordAuthentication)

        //then
        assertThat(authenticationResult).isNull()
    }

    @Test
    fun `refresh 토큰으로 인증하면 AuthenticationException을 반환한다`() {
        //given
        val refreshToken = jwtTokenManager.createRefreshToken(testId)
        val authenticationRequest = JwtAuthenticationRequest(refreshToken)

        //when

        //then
        assertThrows<BadCredentialsException> { sut.authenticate(authenticationRequest) }
    }
}