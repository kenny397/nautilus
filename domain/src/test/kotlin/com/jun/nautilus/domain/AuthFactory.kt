package com.jun.nautilus.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

interface AuthFactoryTest {

    val sut: AuthFactory


    @Test
    fun `userId, eamil과 password를 통해 AuthUser 객체를 생성한다`() {
        //given
        val userId="dummy.id"
        val email="test@email.com"
        val password="asdf"
        //when

        val authUser = sut.create(userId,email,password)
        //then
        assertThat(authUser.userId).isEqualTo(userId)
    }


}