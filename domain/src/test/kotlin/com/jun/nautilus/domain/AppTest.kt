package com.jun.nautilus.domain


import com.jun.nautilus.domain.testhelper.anApp
import com.jun.nautilus.domain.testhelper.anUser
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.UUID

class AppTest {

    @Test
    fun `앱이 관리자인지 확인한다`() {
        //given
        val user1 = anUser(id = UUID.randomUUID().toString(), name = "taki.ng")
        val user2 = anUser(id = UUID.randomUUID().toString(), name = "kenny.seo")
        val app = anApp(owners = setOf(user1))

        //then
        assertThat(app.isOwner(user1)).isTrue
        assertThat(app.isOwner(user2)).isFalse
    }

    @Test
    fun `앱 id가 같으면 같은 앱 입니다  `() {
        //given
        val app1 = anApp(id = UUID.randomUUID().toString(), name = "testApp1")
        val app2 = anApp(id = UUID.randomUUID().toString(), name = "testApp2")

        //when

        //then
        assertThat(app1.id).isEqualTo(app1.id)
        assertThat(app1).isEqualTo(app1)

        assertThat(app2.id).isNotEqualTo(app1.id)
        assertThat(app2).isNotEqualTo(app1)
    }

}