package com.jun.nautilus.server.jpa.repository


import com.jun.nautilus.server.jpa.entity.OwnerEntity
import com.jun.nautilus.server.jpa.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository


interface OwnerEntityRepository: JpaRepository<OwnerEntity,Long> {
    fun findByUser(user: UserEntity): List<OwnerEntity>

}