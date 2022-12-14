package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.auth.impl.AuthRepository
import com.jun.nautilus.auth.AuthUser
import com.jun.nautilus.server.jpa.entity.AuthUserEntity.Companion.from
import org.springframework.stereotype.Repository

@Repository
class JpaAuthUserRepository(
    private val authUserEntityRepository: AuthUserEntityRepository
): AuthRepository {
    override fun save(authUser: AuthUser){
       authUserEntityRepository.save(from(authUser)).toModel()
    }

    override fun findByEmail(email: String): AuthUser? {
        return authUserEntityRepository.findByEmail(email).orElse(null)
            ?.let {it.toModel()}
    }

    override fun findById(id: String): AuthUser? {
        return authUserEntityRepository.findByUserId(id).orElse(null)
            ?.let { it.toModel() }
    }
}