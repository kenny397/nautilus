package com.jun.nautilus.domain

import com.jun.nautilus.domain.testhelper.anAuthUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface AuthRepositoryTest {

    val sut: AuthRepository

    @Test
    fun `AuthUser를 저장한다`() {
        //given
        val authUser = anAuthUser()
        //when
        sut.save(authUser)

        //then
        assertThat(sut.findByEmail(authUser.email)).isEqualTo(authUser)
    }

    @Test
    fun `이메일로 AuthUser를 조회를 할 수 있다`() {
        //given
        val authUser = anAuthUser()
        sut.save(authUser)

        //when
        val foundAuthUser = sut.findByEmail(authUser.email)

        //then
        assertThat(foundAuthUser).isEqualTo(authUser)
    }
}