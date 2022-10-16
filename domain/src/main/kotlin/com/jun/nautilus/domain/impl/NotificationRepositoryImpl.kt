package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.Notification

class NotificationRepositoryImpl: NotificationRepository {

    private val notifications: MutableList<Notification> = mutableListOf()

    override fun save(notification: Notification): Notification {
        return notifications.find { it.id == notification.id }
            ?. let {
                notifications.remove(it)
                notifications.add(notification)
                notification
            }
            ?: let {
                notifications.add(notification)
                notification
            }
    }

    override fun findById(id: String): Notification? {
        return notifications.find {it.id == id}
    }

    override fun deleteById(id: String) {
        notifications.removeIf { it.id == id }
    }

    override fun findDisplayNotificationByApp(app: App): List<Notification> {
        return notifications
            .filter { it.app.id == app.id }
            .filter { it.display() }
    }

    override fun findDisplayNotificationByApp(appId: String): List<Notification> {
        return notifications
            .filter { it.app.id == appId }
            .filter { it.display() }
    }
}