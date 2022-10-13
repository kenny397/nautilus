package com.jun.nautilus.domain


import com.jun.nautilus.domain.testhelper.anNotification
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

interface NotificationRepositoryTest {

    // system under test.
    val sut: NotificationRepository


    @Test
    fun `새로운 공지사항을 저장한다`() {
        //given
        val notification = anNotification()

        //when
        val savedNotification = sut.save(notification)

        //then
        assertThat(savedNotification).isEqualTo(notification)

    }

    @Test
    fun `공지사항 id가 같으면 변경된 공지사항을 저장한다`() {
        //given 
        val id = "dummy.id"
        val notification1 = anNotification(id = id, title = "original title")
        sut.save(notification1)

        // when 
        val notification2 = anNotification(id = id, title = "new title")
        sut.save(notification2)

        // then 
        val foundNotification = sut.findById(id)
        assertThat(foundNotification!!.title).isEqualTo("new title")

    }

    @Test
    fun `공지사항 id로 공지사항을 조회를 할 수 있다`() {
        //given
        val notification = anNotification()
        sut.save(notification)

        //when
        val foundNotification = sut.findById(notification.id)

        //then
        assertThat(foundNotification).isEqualTo(notification)
    }

    @Test
    fun `공지사항 id로 공지사항을 삭제할 수 있다`() {
        //given
        val notification = anNotification()
        sut.save(notification)

        //when
        sut.deleteById(notification.id)

        //then
        val foundNotification = sut.findById(notification.id)
        assertThat(foundNotification).isNull()
    }



    /* kotlin extends
    private fun Notification.copy(
        id: String = this.id,
        title: String = this.title,
        content: String = this.content,
        createdAt: Instant = this.createdAt,
        publishedAt: Instant= this.publishedAt,
        app: App = this.app
    ): Notification = object : Notification {
        override val id: String = id
        override val title: String = title
        override val content: String = content
        override val createdAt: Instant = createdAt
        override val publishedAt: Instant = publishedAt
        override val app: App = app
    }
    */

}