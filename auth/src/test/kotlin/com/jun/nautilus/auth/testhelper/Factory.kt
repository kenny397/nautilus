package com.jun.nautilus.auth.testhelper

import com.jun.nautilus.auth.AuthUser
import io.mockk.coVerify
import java.time.Instant
import java.time.LocalDateTime
import java.util.UUID






fun anAuthUser(
    userId: String = "test",
    email: String = "jun@jun.corp",
    password: String = "aaaaaa"
):AuthUser = TestAuthUser(userId,email, password )

private data class TestAuthUser(
    override val userId: String,
    override val email: String,
    override val password: String
): AuthUser

