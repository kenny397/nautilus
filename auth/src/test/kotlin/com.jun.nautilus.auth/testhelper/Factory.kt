package com.jun.nautilus.auth.testhelper


import com.jun.nautilus.auth.AuthUser


import java.time.Instant





fun anAuthUser(
    userId: String = "test",
    email: String = "jun@jun.corp",
    password: String = "aaaaaa"
): AuthUser = TestAuthUser(userId,email, password )

private data class TestAuthUser(
    override val userId: String,
    override val email: String,
    override val password: String
): AuthUser.Base()

