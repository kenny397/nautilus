package com.jun.nautilus.domain


import com.jun.nautilus.domain.testhelper.anUser
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class UserTest {

    @Test
    fun `id가 같은 유저는 같은 유저 입니다`() {
        //given

        val user1 = anUser(id = UUID.randomUUID().toString())
        val user2 = anUser(id = UUID.randomUUID().toString())

        //when

        //then
        assertThat(user1.id).isEqualTo(user1.id)
        assertThat(user1).isEqualTo(user1)

        assertThat(user2.id).isNotEqualTo(user1.id)
        assertThat(user2).isNotEqualTo(user1)
    }
}