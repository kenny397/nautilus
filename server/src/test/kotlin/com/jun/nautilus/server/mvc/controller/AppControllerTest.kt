package com.jun.nautilus.server.mvc.controller

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jun.nautilus.domain.testhelper.anApp
import com.jun.nautilus.domain.testhelper.anNotification
import com.jun.nautilus.domain.testhelper.anUser
import com.jun.nautilus.server.mvc.service.AppService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest(
    @Autowired
    val mockMvc: MockMvc
)
{
    @MockkBean
    lateinit var appService: AppService

/*
    @Test
    fun `app서비스를 생성하면 201응답한다`() {
        val name ="testName"
        val userId = "userId"

        val appReq = AppCreateRequest(name,userId)
        every { appService.create(appReq) } returns anApp()
        val json = jacksonObjectMapper().registerModule(JavaTimeModule()).writeValueAsString(appReq)


        mockMvc.perform(
            post("/api/apps")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect ( MockMvcResultMatchers.status().isCreated )
    }

    @Test
    fun `app 서비스를 삭제가 성공하면 200응답한다`() {
        //given
        val appId = "testId"

        every { appService.delete(appId) }returns Unit
        //when
        mockMvc.perform(
            delete("/api/apps/${appId}")
        )
            .andExpect(status().isOk)

        //then
    }

    @Test
    fun `app 서비스의 이름 변경에 성공하면 201응답한다`() {
        //given
        val appId="testId"
        val newName ="updateName"
        //when
        every { appService.updateName(appId,newName) } returns anApp(name = newName)

        mockMvc.perform(
            put("/api/apps/${appId}/name")
                .param("newName",newName)
        ).andExpect(status().isCreated)
            .andExpect(jsonPath("$.name").value(newName))
        //then
    }

    @Test
    fun `app 서비스에 관리자를 추가에 성공하면 201응답한다`() {
        //given
        val appId="testAppId"
        val userId="testUserId"

        every { appService.addOwner(appId,userId) } returns Unit

        mockMvc.perform(
            put("/api/apps/owner")
                .param("appId",appId)
                .param("userId",userId)
        ).andExpect(status().isCreated)

        //when

        //then
    }

    @Test
    fun `유저가 가진 app서비스 목록을 조회에 성공하면 200응답한다`() {
        //given
        val userId="testUserId"

        every { appService.findByUser(userId)} returns listOf(anApp())
        //when
        mockMvc.perform(
            get("/api/apps/owner/${userId}")
        ).andExpect(status().isOk)
        //then
    }
*/
}