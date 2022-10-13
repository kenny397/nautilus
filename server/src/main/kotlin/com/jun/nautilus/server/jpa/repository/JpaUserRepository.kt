package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.domain.User
import com.jun.nautilus.domain.UserRepository
import com.jun.nautilus.server.jpa.entity.UserEntity.Companion.from
import org.springframework.stereotype.Repository

@Repository
class JpaUserRepository(
    private val userEntityRepository: UserEntityRepository
) : UserRepository {
    override fun save(user: User): User {

        return userEntityRepository.save(from(user)).toModel()
    }

    override fun findById(userId: String): User? {

        return userEntityRepository.findById(userId).orElse(null)
            ?.let { it.toModel() }

    }

    override fun deleteById(userId: String) {
        userEntityRepository.deleteById(userId)
    }

    override fun findByEmail(email: String): User? {
        return userEntityRepository.findByEmail(email).orElse(null)
            ?.let { it.toModel() }
    }


}