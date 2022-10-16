package com.jun.nautilus.server.mvc.controller


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jun.nautilus.server.mvc.controller.view.NotificationInfo
import com.jun.nautilus.server.mvc.security.JwtTokenManager
import com.jun.nautilus.server.mvc.service.AppService
import com.jun.nautilus.server.mvc.service.AuthService
import com.jun.nautilus.server.mvc.service.NotificationService
import com.jun.nautilus.server.mvc.service.UserContext
import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.clear
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.*
import org.springframework.transaction.annotation.Transactional
import java.time.Instant


@ExtendWith(SpringExtension::class)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest(
    @Autowired
    val mockMvc: MockMvc
)
{
    @MockkBean
    lateinit var userContext: UserContext

    @Autowired
    lateinit var notificationService: NotificationService

    @Autowired
    lateinit var appService: AppService

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var jwtTokenManager: JwtTokenManager

    lateinit var accessToken: String

    lateinit var userId: String

    lateinit var appId: String

    @BeforeEach
    fun setUp(){
        val registerRequest= RegisterRequest("testName", "email@test.com", "password")
        userId = authService.register(registerRequest)

        val appReq = AppCreateRequest("appTestName",userId)
        appId=appService.create(appReq).appId

        accessToken = jwtTokenManager.createAccessToken(userId)
        every { userContext.getCurrentUserId() } returns userId
    }


    private fun createNotification(): NotificationInfo {
        val title = "testTitle"
        val description = "testContent"
        val afterMinutePublished = "0"
        val notiReq = NotificationCreateRequest(title, description, afterMinutePublished, appId)
        return notificationService.create(notiReq)
    }

    @Test
    fun `공지사항을 생성하면 201 응답한다`() {
        val title = "testTitle"
        val description = "testContent"
        val publishedAt = "0"
        val notiReq = NotificationCreateRequest(title, description, publishedAt, appId)

        val json = jacksonObjectMapper().registerModule(JavaTimeModule()).writeValueAsString(notiReq)

        mockMvc.post("/api/notifications"){
            header("Authorization","Bearer ${accessToken}")
            content=json
            contentType= MediaType.APPLICATION_JSON

        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
        }

    }

    @Test
    fun `공지사항 삭제가 성공하면 200 응답한다`() {
        //given
        val notificationId = createNotification().notificationId


        //when
        mockMvc.delete("/api/notifications/${notificationId}"){
            header("Authorization","Bearer ${accessToken}")
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
        }


        //then
    }

    @Test
    fun `공지사항 제목 수정에 성공하면 201 응답한다`() {
        //given
        val notificationId = createNotification().notificationId
        val newTitle = "updateTitle"

        //when
        mockMvc.put("/api/notifications/${notificationId}/title"){
            header("Authorization","Bearer ${accessToken}")
            param("newTitle",newTitle)
        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
        }

        //then
    }

    @Test
    fun `공지사항 내용 수정에 성공하면 201 응답한다`() {
        //given
        val notificationId = createNotification().notificationId
        val newContent = "updateContent"


        //when
        mockMvc.put("/api/notifications/${notificationId}/content") {
            header("Authorization","Bearer ${accessToken}")
            param("newContent", newContent)

        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `공지사항 노출 중지에 성공하면 201 응답한다`() {
        val notificationId = createNotification().notificationId


        //when
        mockMvc.put("/api/notifications/${notificationId}/inactive") {
            header("Authorization","Bearer ${accessToken}")

        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `공지사항 노출 시작에 성공하면 201 응답한다`() {
        val notificationId = createNotification().notificationId


        //when
        mockMvc.put("/api/notifications/${notificationId}/active"){
            header("Authorization","Bearer ${accessToken}")

        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
        }

    }

    @Test
    fun `공지사항 노출 시간을 변경하면 201 응답한다`() {
        val notificationId = createNotification().notificationId
        val newTime = "2022-05-15T17:51:08.219672300Z"



        //when
        mockMvc.put("/api/notifications/${notificationId}/publish") {
            header("Authorization","Bearer ${accessToken}")
            param("newTime",newTime)
        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `선택된 앱의 노출된 공지사항을 List와 200을 응답한다`() {
        createNotification()
        mockMvc.get("/api/notifications/list/${appId}") {

        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
        }
    }

    @Test
    fun `공지사항을 자세히 조회하고 200을 응답한다`(){
        val notificationId = createNotification().notificationId

        mockMvc.get("/api/notifications/${notificationId}"){

        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
        }

    }


}