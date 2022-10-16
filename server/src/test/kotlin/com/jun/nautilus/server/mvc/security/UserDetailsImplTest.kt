package com.jun.nautilus.server.mvc.security

import com.jun.nautilus.auth.testhelper.anAuthUser
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class UserDetailsImplTest {

    private val authUser= anAuthUser("testId","test@test.com","test")

    private val userDetails= UserDetailsImpl(authUser)

    @Test
    fun `권한을 사용하지 않기 때문에 빈 List를 출력한다`() {
        assertThat(userDetails.authorities.size).isEqualTo(0)
    }

    @Test
    fun `password를 UserDetail에 포함하면 안된다`() {
        assertThat(userDetails.password).isNull()
    }

    @Test
    fun `유저ID를 반환한다 `() {
        assertThat(userDetails.username).isEqualTo(authUser.userId)
    }

    @Test
    fun `계정에는 만료기한이 없기때문에 true를 반환한다`() {
        assertThat(userDetails.isAccountNonExpired).isTrue
    }

    @Test
    fun `계정을 막는 기능이 없기 떄문에 true를 반환하다`() {
        assertThat(userDetails.isAccountNonLocked).isTrue

    }

    @Test
    fun `Credentials가 만료가 되지 않으므로 true를 리턴한다`() {
        assertThat(userDetails.isCredentialsNonExpired).isTrue

    }

    @Test
    fun `사용가능한 UserDetail이므로 true를 리턴한다`() {
        assertThat(userDetails.isEnabled).isTrue

    }
}