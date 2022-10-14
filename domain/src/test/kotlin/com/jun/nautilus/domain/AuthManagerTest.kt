package com.jun.nautilus.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test


interface AuthManagerTest {

    val sut: AuthManager
    val authenticator: Authenticator

    @Test
    fun `AuthUser객체를 등록한다`() {
        //given
        val userId = "test"
        val email = "kenny397@jun.corp"
        val password = "asd123"

        //when
        sut.create(userId, email, password)
        //then
        assertThat(authenticator.authenticate(email, password)).isEqualTo(Authenticator.Result.Success)



    }

}