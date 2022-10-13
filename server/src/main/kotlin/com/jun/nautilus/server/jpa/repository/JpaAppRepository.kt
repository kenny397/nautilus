package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.AppRepository
import com.jun.nautilus.domain.User
import com.jun.nautilus.server.jpa.entity.AppEntity.Companion.from
import com.jun.nautilus.server.jpa.entity.UserEntity
import org.springframework.stereotype.Repository

@Repository
class JpaAppRepository (
    private val appEntityRepository: AppEntityRepository,
    private val ownerEntityRepository: OwnerEntityRepository
        ): AppRepository{
    override fun save(app: com.jun.nautilus.domain.App): com.jun.nautilus.domain.App {
        return appEntityRepository.save(from(app)).toModel()
    }

    override fun findById(appId: String): com.jun.nautilus.domain.App? {
        return appEntityRepository.findById(appId).orElse(null)
            ?.let { it.toModel() }
    }

    override fun deleteById(appId: String) {
        appEntityRepository.deleteById(appId)
    }

    override fun findByUser(user: User): List<com.jun.nautilus.domain.App> {
        return ownerEntityRepository.findByUser(UserEntity.from(user)).map { appEntityRepository.findById(it.appId).get().toModel() }.toList()
    }


}