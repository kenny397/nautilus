package com.jun.nautilus.server.mvc.service

import com.jun.nautilus.domain.*
import com.jun.nautilus.domain.impl.NotificationImpl
import com.jun.nautilus.server.mvc.controller.NotificationCreateRequest
import com.jun.nautilus.server.mvc.controller.view.NotificationInfo
import com.jun.nautilus.server.mvc.controller.view.NotificationSimpleInfo
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant


@Service
class NotificationService(
    private val notificationManager: NotificationManager,
    private val appManager: AppManager
)
 {
    @Transactional
    fun create(notiReq: NotificationCreateRequest): NotificationInfo {
        val userId = getAuthentication().name
        checkOwnerByAppId(userId,notiReq.appId)
        val app = appManager.findById(notiReq.appId)
        val durationMinute = notiReq.publishedAt.toLong()*60
        return NotificationInfo.from(notificationManager.create(notiReq.title,notiReq.content, Instant.now().plusSeconds(durationMinute),app))
    }

     @Transactional
     fun delete(notificationId: String) {
         val userId = getAuthentication().name
         checkOwnerByNotificationId(userId, notificationId)
        notificationManager.remove(notificationId)
    }

     @Transactional
     fun updateTitle(notificationId: String,newTitle: String): NotificationInfo {
         val userId = getAuthentication().name
         checkOwnerByNotificationId(userId, notificationId)
        return NotificationInfo.from(notificationManager.updateTitle(notificationId,newTitle))
    }

     @Transactional
     fun updateContent(notificationId: String, newContent: String): NotificationInfo {
         val userId = getAuthentication().name
         checkOwnerByNotificationId(userId, notificationId)
        return NotificationInfo.from(notificationManager.updateContent(notificationId,newContent))
    }

     @Transactional
     fun updatePublishTime(notificationId: String, newTime: String): NotificationInfo {
         val userId = getAuthentication().name
         checkOwnerByNotificationId(userId, notificationId)
        return NotificationInfo.from(notificationManager.updatePublishedAt(notificationId, Instant.parse(newTime)))
    }

     @Transactional
     fun inactive(notificationId: String): NotificationInfo {
         val userId = getAuthentication().name
         checkOwnerByNotificationId(userId, notificationId)
        return NotificationInfo.from(notificationManager.activeOff(notificationId))
    }

     @Transactional
     fun active(notificationId: String): NotificationInfo{
         val userId = getAuthentication().name
         checkOwnerByNotificationId(userId, notificationId)
        return NotificationInfo.from(notificationManager.activeOn(notificationId))
    }

     @Transactional(readOnly = true)
     fun findDisplayNotificationByApp(appId: String): List<NotificationSimpleInfo> {
        return notificationManager.findDisplayNotification(appId).map { NotificationSimpleInfo.from(it) }
    }

     @Transactional(readOnly = true)
     fun findById(notificationId: String): NotificationInfo {

        return NotificationInfo.from(notificationManager.findById(notificationId))
    }

     private fun getAuthentication(): Authentication {
         return SecurityContextHolder.getContext().authentication
     }

     private fun checkOwnerByNotificationId(userId: String, notificationId: String){
         notificationManager.findById(notificationId)
             .let {
                 if(it.app.isOwner(userId))throw NoAppOwnerException(userId+" is not appOwner")
             }
     }

     private fun checkOwnerByAppId(userId: String, appId: String){
         appManager.findById(appId)
             .let {
                 if(!it.isOwner(userId))throw NoAppOwnerException(userId+" is not appOwner")
             }
     }

}