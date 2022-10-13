package com.jun.nautilus.server.mvc.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jun.nautilus.server.mvc.service.AuthService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension


import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.lang.Boolean

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest(
    @Autowired
    val mockMvc: MockMvc
){

    @MockkBean
    lateinit var authService: AuthService


/*
    @Test
    fun `회원가입을 성공하면 201응답을 한다`() {
        //given
        val name = "kenny.seo"
        val email = "kenny@jun.corp"
        val password = "test"

        val registerRequest= RegisterRequest(name, email, password)

        every { authService.register(registerRequest) }returns Unit
        val json = jacksonObjectMapper().writeValueAsString( registerRequest)

        //when
        mockMvc.perform(post("/api/auth/register")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect ( status().isCreated )


        //then
    }

    @Test
    fun `로그인에 성공하면 200응답을 한다`() {
        //given
        val email = "test@test.com"
        val password = "test"

        val loginRequest = LoginRequest(email, password)
        every { authService.login(loginRequest) }returns String()
        //when
        val json = jacksonObjectMapper().writeValueAsString(loginRequest)
        mockMvc.perform(post("/api/auth/login")
            .content(json).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
        //then
    }
*/
}
