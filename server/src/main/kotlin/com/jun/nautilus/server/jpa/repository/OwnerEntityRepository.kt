package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.server.jpa.entity.AppEntity
import com.jun.nautilus.server.jpa.entity.OwnerEntity
import com.jun.nautilus.server.jpa.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OwnerEntityRepository: JpaRepository<OwnerEntity,Long> {
    fun findByUser(user: UserEntity): List<OwnerEntity>

}