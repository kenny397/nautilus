package com.jun.nautilus.domain


import com.jun.nautilus.domain.impl.AppRepository
import com.jun.nautilus.domain.testhelper.anApp
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Instant

interface NotificationManagerTest {

    val sut: NotificationManager

    val appRepository: AppRepository

    @Test
    fun ` 제목, 내용, 노출시간, 앱객체를 가지고 공지사항을 생성한다`() {
        //given
        val app = anApp()

        //when
        val notification = sut.create("testTitle","testContent", Instant.now(), app )

        //then
        assertThat(notification.title).isEqualTo("testTitle")
    }

    @Test
    fun `공지사항을 삭제 할 수 있다`() {
        //given
        val app = anApp()
        val notification = sut.create("testTitle","testContent", Instant.now(), app )
        val foundNotification = sut.findById(notification.id)
        assertThat(notification).isEqualTo(foundNotification)

        //when
        sut.remove(notification.id)

        //then
        assertThrows<NoSuchNotificationException>{sut.findById(notification.id)}
    }

    @Test
    fun `공지사항 제목을 변경 할 수 있다`() {
        //given
        val app = anApp()
        val notification = sut.create("testTitle","testContent", Instant.now(), app )

        //when
        val newTitle = "newTitle"
        val newNotification=sut.updateTitle(notification.id,newTitle)

        //then
        assertThat(newNotification.title).isEqualTo(newTitle)
    }

    @Test
    fun `공지사항 content를 변경 할 수 있다`(){
        //given
        val app = anApp()
        val notification = sut.create("testTitle","testContent", Instant.now(), app )
        //when
        val newContent = "newContent"
        val newNotification =sut.updateContent(notification.id,newContent)

        //then
        assertThat(newNotification.content).isEqualTo(newContent)
    }

    @Test
    fun `노출되어 있는 공지글을 노출중지 시킬 수 있습니다`() {
        //given
        val app = anApp()
        val notification = sut.create("testTitle","testContent", Instant.now(), app )

        //when
        sut.activeOff(notification.id)

        //then
        val foundNotification=sut.findById(notification.id)
        assertThat(foundNotification!!.display()).isFalse
    }

    @Test
    fun `노출중지 되어 있는 공지글을 노출 시킬 수 있습니다`() {
        //given
        val app = anApp()
        val notification = sut.create("testTitle","testContent", Instant.now().minusSeconds(1), app )
        sut.activeOff( notification.id)

        //when
        sut.activeOn( notification.id)

        //then
        val foundNotification =sut.findById(notification.id)
        assertThat(foundNotification!!.display()).isTrue
    }

    @Test
    fun `공지사항을 특정시간 이후에 노출되도록 노출시간을 지정할 수 있다`() {
        //given
        val app = anApp()
        val notification = sut.create("testTitle","testContent", Instant.now().plusSeconds(6000), app )
        assertThat(notification!!.display()).isFalse

        //when
        sut.updatePublishedAt(notification.id, Instant.MIN)

        //then
        val foundNotification = sut.findById(notification.id)
        assertThat(foundNotification!!.display()).isTrue
    }

    @Test
    fun `공지사항에 모든 수정사항을 한번에 변경할 수 있다`(){

        //given
        val app = anApp()
        val notification = sut.create("testTitle","testContent", Instant.now().plusSeconds(6000), app )

        //when
        val newTitle="updateTitle"
        val newContent = "updateContent"
        val newPublishedAt = Instant.MIN
        val updateRequest=NotificationManager.UpdateRequest(newTitle,newContent,newPublishedAt)
        sut.update(notification.id,updateRequest)

        //then
        val findNotification = sut.findById(notification.id)
        assertThat(findNotification.title).isEqualTo(newTitle)
        assertThat(findNotification.content).isEqualTo(newContent)
        assertThat(findNotification.publishedAt).isEqualTo(newPublishedAt)

    }

    @Test
    fun `앱에 등록된 공지사항 목록을 조회 할 수 있다`() {
        //given
        val app = anApp()
        appRepository.save(app)
        val notification1 = sut.create("testTitle1","testContent1", Instant.now().minusSeconds(1), app )
        val notification2 = sut.create("testTitle2","testContent2", Instant.now().minusSeconds(1), app )
        val notification3 = sut.create("testTitle3","testContent3", Instant.now().plusSeconds(6000), app )
        //when
        val notifications = sut.findDisplayNotification(app.id)

        //then
        assertThat(notifications).isEqualTo(listOf(notification1,notification2))
    }

    @Test
    fun `앱 객체로 앱에 등록된 공지사항 목록을 조회 할 수 있다`() {
        //given
        val app = anApp()
        appRepository.save(app)
        val notification1 = sut.create("testTitle1","testContent1", Instant.now().minusSeconds(1), app )
        val notification2 = sut.create("testTitle2","testContent2", Instant.now().minusSeconds(1), app )
        val notification3 = sut.create("testTitle3","testContent3", Instant.now().plusSeconds(6000), app )
        //when
        val notifications = sut.findDisplayNotification(app)

        assertThat(notifications).isEqualTo(listOf(notification1,notification2))

    }





}