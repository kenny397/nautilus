package com.jun.nautilus.domain

import com.jun.nautilus.domain.impl.UserRepositoryImpl
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

interface UserServiceTest {

    val sut: UserService



    @Test
    fun `name과 email를 통해 유저를 만들 수 있다`() {
        //given

        //when
        val user = sut.create("testName","Julia@007.com")

        //then
        assertThat(user.name).isEqualTo("testName")
    }

    @Test
    fun `유저를 삭제 할 수 있다`() {
        //given
        val user = sut.create("testName","test@email.com")
        val foundUser = sut.findById(user.id)
        assertThat(user).isEqualTo(foundUser)

        //when
        sut.remove(user.id)

        //then
        assertThrows<NoSuchUserException>{sut.findById(user.id)}
    }

    @Test
    fun `이메일을 가지고 유저를 찾을 수 있다`() {
        //given
        val user = sut.create("testName","test@email.com")

        //when
        val foundUser = sut.findByEmail(user.email)

        //then
        assertThat(user.email).isEqualTo(foundUser.email)
    }

    @Test
    fun `유저를 생성할 때 이메일 양식이 아니면 IllegalArgumentException을 던진다`() {
        //given
        val name = "test"
        val email = "test.test.notEmail"

        //when

        //then
        assertThrows<IllegalArgumentException>{sut.create(name,email)}
    }

    @Test
    fun `유저를 생성할 때 존재하는 이메일이면 EmailExistException을 던진다`() {
        //given
        val user = sut.create("testName","test@email.com")

        //when

        //then
        assertThrows<EmailExistException>{sut.create("testName2",user.email)}
    }

}
