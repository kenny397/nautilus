package com.jun.nautilus.server.mvc.service

import com.jun.nautilus.domain.*
import com.jun.nautilus.server.jpa.entity.AppEntity
import com.jun.nautilus.server.jpa.entity.OwnerEntity
import com.jun.nautilus.server.jpa.entity.UserEntity
import com.jun.nautilus.server.jpa.repository.OwnerEntityRepository
import com.jun.nautilus.server.mvc.controller.AppCreateRequest
import com.jun.nautilus.server.mvc.controller.view.AppInfo
import com.jun.nautilus.server.mvc.controller.view.AppSimpleInfo
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class AppService(
    private val appManager: AppManager,
    private val userService: UserService,
) {

    @Transactional
    fun create(appReq: AppCreateRequest): AppInfo {
        val owner = userService.findById(appReq.userId)

        return AppInfo.from(appManager.create(appReq.name,owner))
    }

    @Transactional
    fun delete(appId: String) {
        val userId = getAuthentication().name
        checkOwnerByAppId(userId,appId)
        appManager.remove(appId)
    }

    @Transactional
    fun updateName(appId: String, newName: String): AppInfo {
        val userId = getAuthentication().name
        checkOwnerByAppId(userId,appId)
        return AppInfo.from(appManager.updateName(appId,newName))
    }

    @Transactional
    fun addOwner(appId: String, userId: String){
        val userId = getAuthentication().name
        checkOwnerByAppId(userId,appId)
        val newOwner = userService.findById(userId)
        appManager.addOwner(appId,newOwner)
    }

    @Transactional(readOnly = true)
    fun findByUser(userId: String): List<AppSimpleInfo> {
        val user = userService.findById(userId)
        return appManager.findByUser(user).map { AppSimpleInfo.from(it) }
    }

    private fun getAuthentication(): Authentication{
        // ThreadLocal
        return SecurityContextHolder.getContext().authentication
    }

    private fun checkOwnerByAppId(userId: String, appId: String){
        appManager.findById(appId)
            .let {
                if(it.isOwner(userId))throw NoAppOwnerException(userId+"is not appOwner")
            }
    }
}

interface CurrentUserProvider {
    fun getCurrentUser() : User
}