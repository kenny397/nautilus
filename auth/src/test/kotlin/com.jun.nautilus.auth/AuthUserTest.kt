package com.jun.nautilus.auth


import com.jun.nautilus.auth.testhelper.anAuthUser
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class AuthUserTest {

    @Test
    fun `유저 id가 같으면 같은 앱 입니다  `() {
        //given
        val authUser1 = anAuthUser(userId = UUID.randomUUID().toString())
        val authUser2 = anAuthUser(userId = UUID.randomUUID().toString())
        val authUser3 = anAuthUser(userId = authUser1.userId)
        //when

        //then
        assertThat(authUser1.userId).isEqualTo(authUser1.userId)
        assertThat(authUser1).isEqualTo(authUser1)

        assertThat(authUser2.userId).isNotEqualTo(authUser1.userId)
        assertThat(authUser2).isNotEqualTo(authUser1)

        assertThat(authUser3).isEqualTo(authUser1)
    }
}