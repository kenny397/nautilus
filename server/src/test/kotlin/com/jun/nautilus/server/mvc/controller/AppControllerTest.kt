package com.jun.nautilus.server.mvc.controller


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jun.nautilus.server.mvc.controller.view.AppInfo
import com.jun.nautilus.server.mvc.security.JwtTokenManager
import com.jun.nautilus.server.mvc.service.AppService
import com.jun.nautilus.server.mvc.service.AuthService
import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.*
import org.springframework.transaction.annotation.Transactional


@ExtendWith(SpringExtension::class)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest(
    @Autowired
    val mockMvc: MockMvc
)
{
    @Autowired
    lateinit var appService: AppService

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var jwtTokenManager: JwtTokenManager

    lateinit var accessToken: String

    lateinit var userId: String
    @BeforeEach
    fun setUp(){
        val registerRequest= RegisterRequest("testName", "email@test.com", "password")
        userId = authService.register(registerRequest)
        accessToken = jwtTokenManager.createAccessToken(userId)
    }

    private fun createApp(): AppInfo{
        val name ="testName"
        val appReq = AppCreateRequest(name,userId)
        return appService.create(appReq)
    }

    @Test
    fun `app서비스를 생성하면 201응답한다`() {
        val name ="testName"

        val appReq = AppCreateRequest(name,userId)

        val json = jacksonObjectMapper().registerModule(JavaTimeModule()).writeValueAsString(appReq)


        mockMvc.post("/api/apps"){
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
    fun `app 서비스를 삭제가 성공하면 200응답한다`() {
        //given
        val appInfo = createApp()


        //when
        mockMvc.delete("/api/apps/${appInfo.appId}") {
            header("Authorization","Bearer ${accessToken}")
        }.andDo{
            print()
        }.andExpect {
            status { isOk() }
        }


        //then
    }

    @Test
    fun `app 서비스의 이름 변경에 성공하면 201응답한다`() {
        //given
        val appInfo = createApp()
        val newName ="updateName"
        //when


        mockMvc.put("/api/apps/${appInfo.appId}/name"){
            header("Authorization","Bearer ${accessToken}")
            param("newName", newName)
        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
        }


    }

    @Test
    fun `app 서비스에 관리자를 추가에 성공하면 201응답한다`() {
        //given
        val appInfo = createApp()
        mockMvc.put("/api/apps/owner"){
            header("Authorization","Bearer ${accessToken}")
            param("appId",appInfo.appId)
            param("userId",userId)
        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `유저가 가진 app서비스 목록을 조회에 성공하면 200응답한다`() {
        createApp()

        mockMvc.get("/api/apps/owner/${userId}"){
            header("Authorization","Bearer ${accessToken}")
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
        }


    }

}