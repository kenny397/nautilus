package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.server.jpa.entity.AuthUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AuthUserEntityRepository: JpaRepository<AuthUserEntity, String> {
    fun findByEmail(email: String): Optional<AuthUserEntity>

    fun findByUserId(id: String): Optional<AuthUserEntity>
}