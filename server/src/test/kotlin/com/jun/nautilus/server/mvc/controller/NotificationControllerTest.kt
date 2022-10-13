package com.jun.nautilus.server.mvc.controller

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jun.nautilus.domain.Notification
import com.jun.nautilus.domain.testhelper.anNotification
import com.jun.nautilus.server.mvc.service.NotificationService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest(
    @Autowired
    val mockMvc: MockMvc
)
{
    @MockkBean
    lateinit var notificationService: NotificationService

/*


    @Test
    fun `공지사항을 생성하면 201 응답한다`() {
        val title = "testTitle"
        val content = "testContent"
        val publishedAt = Instant.now().toString()
        val appId = "aaa"

        val notiReq = NotificationCreateRequest(title, content, publishedAt, appId)



        every { notificationService.create(notiReq) } returns anNotification()
        val json = jacksonObjectMapper().registerModule(JavaTimeModule()).writeValueAsString(notiReq)


        mockMvc.perform(
            post("/api/notifications")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect ( status().isCreated )

    }

    @Test
    fun `공지사항 삭제가 성공하면 200 응답한다`() {
        //given
        val notificationId = "asd"
        every { notificationService.delete(notificationId) } returns Unit

        //when
        mockMvc.perform(
            delete("/api/notifications/${notificationId}")
        )
            .andExpect( status().isOk)
        //then
    }

    @Test
    fun `공지사항 제목 수정에 성공하면 201 응답한다`() {
        //given
        val notificationId = "123"
        val newTitle = "updateTitle"

        every { notificationService.updateTitle(notificationId,newTitle) } returns anNotification(title = newTitle)

        //when
        mockMvc.perform(
            put("/api/notifications/${notificationId}/title")
                .param("newTitle",newTitle)
        ).andExpect(status().isCreated)
            .andExpect(jsonPath("$.title").value(newTitle))
        //then
    }

    @Test
    fun `공지사항 내용 수정에 성공하면 201 응답한다`() {
        //given
        val notificationId = "123"
        val newContent = "updateContent"


        every { notificationService.updateContent(notificationId,newContent) } returns anNotification(content = newContent)

        //when
        mockMvc.perform(
            put("/api/notifications/${notificationId}/content")
                .param("newContent",newContent)
        ).andExpect(status().isCreated)
            .andExpect(jsonPath("$.content").value(newContent))
    }

    @Test
    fun `공지사항 노출 중지에 성공하면 201 응답한다`() {
        val notificationId = "123"

        every { notificationService.inactive(notificationId) } returns anNotification(active = false)

        //when
        mockMvc.perform(
            put("/api/notifications/${notificationId}/inactive")
        ).andExpect(status().isCreated)
            .andExpect(jsonPath("$.active").value(false))
    }

    @Test
    fun `공지사항 노출 시작에 성공하면 201 응답한다`() {
        val notificationId = "123"

        every { notificationService.active(notificationId) } returns anNotification(active = true)

        //when
        mockMvc.perform(
            put("/api/notifications/${notificationId}/active")
        ).andExpect(status().isCreated)
            .andExpect(jsonPath("$.active").value(true))
    }

    @Test
    fun `공지사항 노출 시간을 변경하면 201 응답한다`() {
        val notificationId = "123"
        val newTime = "2022-05-15T17:51:08.219672300Z"


        every { notificationService.updatePublishTime(notificationId,newTime) } returns anNotification(publishedAt = Instant.parse(newTime))

        //when
        mockMvc.perform(
            put("/api/notifications/${notificationId}/publish")
                .param("newTime",newTime)
        ).andExpect(status().isCreated)
            .andExpect(jsonPath("$.publishedAt").value(newTime))
    }

    @Test
    fun `선택된 앱의 노출된 공지사항을 List와 200을 응답한다`() {
        val appId = "123"

        every { notificationService.findDisplayNotificationByApp(appId) }returns listOf<Notification>(anNotification())

        mockMvc.perform(
            get("/api/notifications/list/${appId}")
        ).andExpect(status().isOk)
    }

    @Test
    fun `공지사항을 자세히 조회하고 200을 응답한다`(){
        val notificationId="123"

        every { notificationService.findById(notificationId) }returns anNotification()

        mockMvc.perform(
            get("/api/notifications/${notificationId}")
        ).andExpect(status().isOk)

    }
*/

}