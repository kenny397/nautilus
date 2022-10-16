package com.jun.nautilus.domain


import com.jun.nautilus.domain.impl.NotificationRepository
import com.jun.nautilus.domain.testhelper.anApp
import com.jun.nautilus.domain.testhelper.anNotification
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

interface NotificationRepositoryTest {


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

    @Test
    fun `앱을 통해 노출되어 있는 공지사항을 조회 할 수 있다`(){
        //given
        val app = anApp()

        val notification1 = anNotification(id = "dummyId1", title = "original title", app = app)
        sut.save(notification1)
        val notification2 = anNotification(id = "dummyId2", title = "new title", app = app)
        sut.save(notification2)
        //when
        val foundNotifications = sut.findDisplayNotificationByApp(app)

        //then
        assertThat(foundNotifications.size).isEqualTo(2)
    }

    @Test
    fun `앱id를 통해 노출되어 있는 공지사항을 조회 할 수 있다`(){
        //given
        val app = anApp()

        val notification1 = anNotification(id = "dummyId1", title = "original title", app = app)
        sut.save(notification1)
        val notification2 = anNotification(id = "dummyId2", title = "new title", app = app)
        sut.save(notification2)
        //when
        val foundNotifications = sut.findDisplayNotificationByApp(app.id)

        //then
        assertThat(foundNotifications.size).isEqualTo(2)
    }






}