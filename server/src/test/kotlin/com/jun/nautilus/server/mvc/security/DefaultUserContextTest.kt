package com.jun.nautilus.server.mvc.security

import com.jun.nautilus.auth.testhelper.anAuthUser
import com.jun.nautilus.server.mvc.service.UserContext
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.springframework.security.core.context.SecurityContextHolder

internal class DefaultUserContextTest {


    private val authUser= anAuthUser("testId","test@test.com","test")

    private val userDetails= UserDetailsImpl(authUser)
    private val jwt = "test"

    lateinit var sut: UserContext
    @BeforeEach
    fun setUp(){
        sut = DefaultUserContext()

    }

    @Test
    fun `시큐리티컨텍스트 홀더에 있는 userId정보를 가져올 수 있다`() {
        SecurityContextHolder.getContext().authentication = JwtAuthenticationResult(userDetails,jwt)
        val currentUserId = sut.getCurrentUserId()

        assertThat(currentUserId).isEqualTo(userDetails.username)
    }

    @Test
    fun `시큐리티 컨텍스트 홀더에 정보가 없으면 NoUserContextException을 던진다`() {

        assertThrows<NoUserContextException> { sut.getCurrentUserId() }
    }
}