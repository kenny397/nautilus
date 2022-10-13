package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.Notification
import com.jun.nautilus.domain.NotificationRepository
import com.jun.nautilus.server.jpa.entity.NotificationEntity.Companion.from
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class JpaNotificationRepository(
    private val notificationEntityRepository: NotificationEntityRepository
) : NotificationRepository{
    override fun save(notification: Notification): Notification {
        return notificationEntityRepository.save(from(notification)).toModel()
    }

    override fun findById(id: String): Notification? {
        return notificationEntityRepository.findById(id).orElse(null)
            ?.let { it.toModel() }
    }

    override fun deleteById(id: String) {
        notificationEntityRepository.deleteById(id)
    }

    override fun findDisplayNotificationByApp(app: com.jun.nautilus.domain.App): List<Notification> {

        return notificationEntityRepository
            .findDisplayNotificationByApp(app.id, true,Instant.now())
            .map{it.toModel()}
    }

    override fun findDisplayNotificationByApp(appId: String): List<Notification> {
        return notificationEntityRepository.findDisplayNotificationByApp(appId,true ,Instant.now())
            .map{it.toModel()}
    }

}