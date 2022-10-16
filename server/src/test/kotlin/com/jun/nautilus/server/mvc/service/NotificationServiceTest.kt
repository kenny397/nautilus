package com.jun.nautilus.server.mvc.service

import com.jun.nautilus.domain.AppManager
import com.jun.nautilus.domain.NotificationManager
import com.jun.nautilus.domain.testhelper.anApp
import com.jun.nautilus.domain.testhelper.anNotification
import com.jun.nautilus.domain.testhelper.anUser
import com.jun.nautilus.server.mvc.controller.NotificationCreateRequest
import com.jun.nautilus.server.mvc.error.NoAppOwnerException
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Instant

@ExtendWith(MockKExtension::class)
internal class NotificationServiceTest {

    @MockK
    lateinit var notificationManager: NotificationManager

    @MockK
    lateinit var appManager: AppManager

    @MockK
    lateinit var userContext: UserContext

    lateinit var sut: NotificationService

    @BeforeEach
    fun setUp() {
        every { userContext.getCurrentUserId() } returns "userTestId1"
        sut = NotificationService(notificationManager, appManager,userContext)
    }

    @Test
    fun `공지사항을 생성할 때 생성하는 사람이 앱의 관리자가 아니면 NoAppOwnerException 예외를 던진다`() {
        //given
        val appId ="appTestId"
        every { appManager.findById(appId) } returns anApp(owners = setOf(anUser(id = "userTestId2")))
        val notiReq = NotificationCreateRequest("test","test","10",appId)

        assertThrows<NoAppOwnerException> { sut.create(notiReq) }


    }

    @Test
    fun `공지사항을 삭제할 때 생성하는 사람이 앱의 관리자가 아니면 NoAppOwnerException 예외를 던진다`() {
        //given
        val notificationId ="notificationTestId"
        every { notificationManager.findById(notificationId) } returns anNotification(app = anApp(owners = setOf(anUser(id = "userTestId2"))))

        assertThrows<NoAppOwnerException> { sut.delete(notificationId) }
    }

    @Test
    fun `공지사항 제목을 수정할  때 생성하는 사람이 앱의 관리자가 아니면 NoAppOwnerException 예외를 던진다`() {
        //given
        val notificationId ="notificationTestId"
        every { notificationManager.findById(notificationId) } returns anNotification(app = anApp(owners = setOf(anUser(id = "userTestId2"))))

        assertThrows<NoAppOwnerException> { sut.updateTitle(notificationId,"newTitle") }
    }

    @Test
    fun `공지사항 내용을 수정할 때 생성하는 사람이 앱의 관리자가 아니면 NoAppOwnerException 예외를 던진다`() {
        //given
        val notificationId ="notificationTestId"
        every { notificationManager.findById(notificationId) } returns anNotification(app = anApp(owners = setOf(anUser(id = "userTestId2"))))

        assertThrows<NoAppOwnerException> { sut.updateContent(notificationId,"newContent") }
    }

    @Test
    fun `공지사항 노출시간을 수정할 때 생성하는 사람이 앱의 관리자가 아니면 NoAppOwnerException 예외를 던진다`() {
        val notificationId ="notificationTestId"
        every { notificationManager.findById(notificationId) } returns anNotification(app = anApp(owners = setOf(anUser(id = "userTestId2"))))

        assertThrows<NoAppOwnerException> { sut.updatePublishTime(notificationId, Instant.now().toString()) }
    }

    @Test
    fun `공지사항 노출 중지할 때 생성하는 사람이 앱의 관리자가 아니면 NoAppOwnerException 예외를 던진다`() {
        val notificationId ="notificationTestId"
        every { notificationManager.findById(notificationId) } returns anNotification(app = anApp(owners = setOf(anUser(id = "userTestId2"))))

        assertThrows<NoAppOwnerException> { sut.inactive(notificationId) }
    }

    @Test
    fun `공지사항 노출을 다시 시작할 때 생성하는 사람이 앱의 관리자가 아니면 NoAppOwnerException 예외를 던진다`() {
        val notificationId ="notificationTestId"
        every { notificationManager.findById(notificationId) } returns anNotification(app = anApp(owners = setOf(anUser(id = "userTestId2"))))

        assertThrows<NoAppOwnerException> { sut.active(notificationId) }
    }

}