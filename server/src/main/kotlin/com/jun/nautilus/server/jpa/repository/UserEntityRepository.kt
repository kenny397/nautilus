package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.server.jpa.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserEntityRepository: JpaRepository<UserEntity,String> {
    fun findByEmail(email: String): Optional<UserEntity>
}