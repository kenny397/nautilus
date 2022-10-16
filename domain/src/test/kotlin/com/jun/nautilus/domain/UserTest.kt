package com.jun.nautilus.domain


import com.jun.nautilus.domain.testhelper.anUser
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class UserTest {

    @Test
    fun `유저가 같으면 같은 유저 입니다`() {
        //given

        val user1 = anUser(id = UUID.randomUUID().toString())
        val user2 = anUser(id = UUID.randomUUID().toString())
        val user3 = anUser(id = user1.id)

        //when

        //then
        assertThat(user1.id).isEqualTo(user1.id)
        assertThat(user1).isEqualTo(user1)

        assertThat(user2.id).isNotEqualTo(user1.id)
        assertThat(user2).isNotEqualTo(user1)

        assertThat(user3).isEqualTo(user1)
    }
}