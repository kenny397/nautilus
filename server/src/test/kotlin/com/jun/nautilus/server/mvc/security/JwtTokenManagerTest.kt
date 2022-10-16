package com.jun.nautilus.server.mvc.security

import io.jsonwebtoken.ExpiredJwtException
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


internal class JwtTokenManagerTest{

    private lateinit var sut: JwtTokenManager

    @BeforeEach
    fun setUp(){
        sut = JwtTokenManager()
    }

    @Test
    fun `userId를 받아서 accessToken을 생성할 수 있다`() {
        //given
        val userId = "testId"

        //when
        val accessToken = sut.createAccessToken(userId)

        //then
        val verification = sut.verifyToken(accessToken)
        assertThat(verification.type).isEqualTo(TokenType.Access)
        assertThat(verification.userId).isEqualTo(userId)

    }

    @Test
    fun `userId를 받아서 refreshToken을 생성할 수 있다`() {
        //given
        val userId = "testId"

        //when
        val refreshToken = sut.createRefreshToken(userId)
        //then
        val verification = sut.verifyToken(refreshToken)
        assertThat(verification.type).isEqualTo(TokenType.Refresh)
        assertThat(verification.userId).isEqualTo(userId)

    }

    @Test
    fun `잘못된 토큰을 파싱하면 InvalidAuthTokenException 를 던진다`() {
        //given
        val wrongToken = "wrongJwtToken"

        //then
        assertThrows<InvalidAuthTokenException> { sut.verifyToken(wrongToken) }
    }

    @Test
    fun `만료된 토큰은 ExpiredJwtException을  확인할 수 있다`() {
        //given
        val accessToken = sut.createAccessToken("test",-100)
        println(accessToken)
        //then
        assertThrows<ExpiredJwtException> { sut.verifyToken(accessToken) }
    }


}