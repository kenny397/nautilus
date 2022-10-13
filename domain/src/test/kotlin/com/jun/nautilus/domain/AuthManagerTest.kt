package com.jun.nautilus.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test


interface AuthManagerTest {

    val sut: AuthManager

    @Test
    fun `AuthUser객체를 등록한다`() {
        //given
        val userId = "test"
        val email = "kenny397@jun.corp"
        val password = "asd123"

        //when
        val authUser = sut.register(userId, email, password)
        //then
        assertThat(authUser.userId).isEqualTo(userId)

        assertThat(sut.login(email, password)).isTrue

    }


    @Test
    fun `이메일 과 패스워드를 통해 인증을 할 수 있습니다`() {
        //given
        val id = "dummyId"
        val email = "kenny397@jun.corp"
        val password = "asd123"
        val authUser= sut.register(id,email,password)


        //when


        val result = sut.login(email,password)

        //then


        assertThat(result).isTrue
    }
}