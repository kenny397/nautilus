package com.jun.nautilus.server.mvc.security

import com.jun.nautilus.domain.testhelper.anAuthUser
import com.jun.nautilus.domain.testhelper.anUser
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class JwtAuthenticationResultTest {

    private val authUser = anAuthUser()
    private val principal = UserDetailsImpl(authUser)
    private val jwt = "test"
    private val authenticationResult = JwtAuthenticationResult(principal,jwt)

    @Test
    fun `userId를 반환한다`() {
        assertThat(authUser.userId).isEqualTo(authenticationResult.name)
    }

    @Test
    fun `권한을 사용하지 않으므로 빈 리스트를 반환한다`() {
        assertThat(authenticationResult.authorities.size).isEqualTo(0)
    }

    @Test
    fun `principal를 증명할 수 있는 jwt를 반환한다`(){
        assertThat(authenticationResult.credentials).isEqualTo(jwt)

    }

    @Test
    fun `추가적인 요청이 없으므로 null을 반환한다`() {
        assertThat(authenticationResult.details).isNull()
    }

    @Test
    fun `principal인 userDetail 객체를 반환한다`() {
        assertThat(authenticationResult.principal).isEqualTo(principal)
    }

    @Test
    fun `인증을 끝낸 결과는 인증값을 true를 반환한다`() {
        assertThat(authenticationResult.isAuthenticated).isTrue
    }

    @Test
    fun `인증 결과를 조작하려고 하면 IllegalArguementException을 던진다`() {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> { authenticationResult.isAuthenticated=true }
    }
}