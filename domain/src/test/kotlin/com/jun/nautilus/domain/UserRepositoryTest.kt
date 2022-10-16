package com.jun.nautilus.domain



import com.jun.nautilus.domain.impl.UserRepository
import com.jun.nautilus.domain.testhelper.anUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface UserRepositoryTest {

    val sut: UserRepository

    @Test
    fun `새로운 유저를 저장한다`(){
        //given
        val user = anUser()

        //when
        val savedUser = sut.save(user)

        //then
        assertThat(savedUser).isEqualTo(user)
    }




    @Test
    fun `유저 id로 유저를 조회를 할 수 있다`(){
        //given
        val user = anUser()
        sut.save(user)

        //when
        val foundUser = sut.findById(user.id)

        //then
        assertThat(foundUser).isEqualTo(user)
    }


    @Test
    fun `유저 id로 유저 단건을 삭제할 수 있다`(){
        //given
        val user = anUser()
        sut.save(user)

        //when
        sut.deleteById(user.id)

        //then
        val foundUser = sut.findById(user.id)
        assertThat(foundUser).isNull()
    }

    @Test
    fun `유저 email로 유저를 조회를 할 수 있다`() {
        //given
        val user = anUser(email = "kenny@jun.corp")
        sut.save(user)

        //when
        val foundUser = sut.findByEmail(user.email)

        //then
        assertThat(foundUser).isEqualTo(user)

    }
}