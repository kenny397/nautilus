package com.jun.nautilus.domain

import com.jun.nautilus.domain.testhelper.anAuthUser
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

interface AuthenticatorTest {

    val sut: Authenticator
    val authManager: AuthManager


    @Test
    fun `사용자 인증을 성공하면 Success를 반환한다`(){
        //given
        val id = "111"
        val email ="test@test.com"
        val password = "test"
        authManager.register(id,email,password)

        //when
        val result=sut.authenticate(email, password)

        //then
        assertThat(result).isEqualTo(Authenticator.Result.Success)

    }

    @Test
    fun `사용자 인증을 실패하면 fail를 반환한다`(){
        val id = "111"
        val email ="test@test.com"
        val password = "test"
        val wrongPassword = "test2"
        authManager.register(id,email,password)

        //when
        val result=sut.authenticate(email, wrongPassword)

        //then
        assertThat(result).isEqualTo(Authenticator.Result.Failed)
    }
}