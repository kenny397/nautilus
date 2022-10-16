package com.jun.nautilus.server.mvc.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jun.nautilus.server.mvc.controller.view.AuthInfo
import com.jun.nautilus.server.mvc.security.JwtTokenManager
import com.jun.nautilus.server.mvc.service.AuthService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional


@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class AuthControllerTest(
    @Autowired
    val mockMvc: MockMvc
){

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var jwtTokenManager: JwtTokenManager


    private fun createUser(name: String, email: String,password: String): String{
        val registerRequest= RegisterRequest(name, email, password)
        return authService.register(registerRequest)
    }



    @Test
    fun `회원가입을 성공하면 201응답을 한다`() {

        val name = "kenny.seo"
        val email = "kenny@jun.corp"
        val password = "test"

        val registerRequest= RegisterRequest(name, email, password)


        val json = jacksonObjectMapper().writeValueAsString( registerRequest)

        mockMvc.post("/api/auth/register"){
            contentType=MediaType.APPLICATION_JSON
            content=json
        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `로그인에 성공하면 200응답을 한다`() {

        val email = "tes214t@test.com"
        val name ="test"
        val password = "test"
        createUser(name,email, password)


        val loginRequest = LoginRequest(email, password)

        val json = jacksonObjectMapper().writeValueAsString(loginRequest)
        mockMvc.post("/api/auth/login"){
            content=json
            contentType=MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status{isOk()}
        }

    }

    @Test
    fun `refresh 토큰을 재발급에 성공하면 200응답을 한다`() {
        val email = "tes214t@test.com"
        val name ="test"
        val password = "test"
        val userId=createUser(name,email, password)
        val refreshToken = jwtTokenManager.createRefreshToken(userId)

        mockMvc.post("/api/auth/reissueToken"){
            param("refreshToken",refreshToken)
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
        }
    }
}
