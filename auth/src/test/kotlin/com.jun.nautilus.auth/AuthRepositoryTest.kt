package com.jun.nautilus.auth

import com.jun.nautilus.auth.impl.AuthRepository
import com.jun.nautilus.auth.testhelper.anAuthUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface AuthRepositoryTest {

    val sut: AuthRepository



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

    @Test
    fun `userId로 AuthUser를 조회할 수 있다`() {
        //given
        val authUser = anAuthUser()
        sut.save(authUser)


        //when
        val foundAuthUser = sut.findById(authUser.userId)

        //then
        assertThat(foundAuthUser).isEqualTo(authUser)

    }
}