package com.jun.nautilus.server.mvc.security


import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class JwtAuthenticationRequestTest {

    private val jwt = "test"
    private val authenticationRequest = JwtAuthenticationRequest(jwt)

    @Test
    fun `authentication 요청에는 userId가 없으므로 빈문자열을 반환한다`() {
        assertThat("").isEqualTo(authenticationRequest.name)
    }

    @Test
    fun `권한을 사용하지 않으므로 빈 리스트를 반환한다`() {
        assertThat(authenticationRequest.authorities.size).isEqualTo(0)
    }

    @Test
    fun `principal를 증명할 수 있는 jwt를 반환한다`(){
        assertThat(authenticationRequest.credentials).isEqualTo(jwt)

    }

    @Test
    fun `추가적인 요청이 없으므로 null을 반환한다`() {
        assertThat(authenticationRequest.details).isNull()
    }

    @Test
    fun `principal이 될 수 있는 jwt를 반환한다`() {
        assertThat(authenticationRequest.principal).isEqualTo(jwt)
    }

    @Test
    fun `인증을 끝낸 결과는 인증값을 false를 반환한다`() {
        assertThat(authenticationRequest.isAuthenticated).isFalse
    }

    @Test
    fun `인증 결과를 조작하려고 하면 IllegalArguementException을 던진다`() {
        assertThrows<IllegalArgumentException> { authenticationRequest.isAuthenticated=true }
    }
    @Test
    fun `jwt 조회 가능`() {
        assertThat(authenticationRequest.jwt).isEqualTo(jwt)
    }
}