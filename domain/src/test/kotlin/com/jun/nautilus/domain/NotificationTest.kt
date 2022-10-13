package com.jun.nautilus.domain


import com.jun.nautilus.domain.testhelper.anNotification
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.util.UUID

class NotificationTest {


    @Test
    fun `공지사항이 노출여부를 확인합니다`() {
        //given
        val notification1 = anNotification(id="test1",publishedAt = Instant.now().minusSeconds(6000))
        val notification2 = anNotification(id="test2",publishedAt = Instant.now().plusSeconds(6000))
        val notification3 = anNotification(id="test3",publishedAt = Instant.now().minusSeconds(6000), active = true)
        val notification4 = anNotification(id="test4",publishedAt = Instant.now(), active = false)


        //when

        //then

        assertThat(notification1.display()).isTrue
        assertThat(notification2.display()).isFalse
        assertThat(notification3.display()).isTrue
        assertThat(notification4.display()).isFalse
    }

    @Test
    fun `공지사항 id가 같으면 같은 공지사항입니다`() {
        //given
        val id1 = "1"
        val id2 = "2"
        val notification1 = anNotification(id = id1)
        val notification2 = anNotification(id = id2)

        //when

        //then
        assertThat(notification1.id).isEqualTo(notification1.id)
        assertThat(notification1).isEqualTo(notification1)

        assertThat(notification2.id).isNotEqualTo(notification1.id)
        assertThat(notification2).isNotEqualTo(notification1)
    }
}