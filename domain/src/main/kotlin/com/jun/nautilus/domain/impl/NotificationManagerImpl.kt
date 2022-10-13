package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.*
import java.time.Instant

class NotificationManagerImpl(
    private val notificationIdGenerator: StringIdGenerator,
    private val notificationRepository: NotificationRepository,
) : NotificationManager {

    override fun create(title: String, content: String, publishedAt: Instant, app: com.jun.nautilus.domain.App): Notification {

        return notificationRepository.save(
            NotificationImpl(
                notificationIdGenerator.generate(),
                title,
                content,
                publishedAt,
                app
            )
        )
    }

    override fun findById(id: String): Notification {
        return notificationRepository.findById(id)
            ?. let {it}
            ?: throw NoSuchNotificationException(id + "is not exist")
    }

    override fun remove(id: String) {

        notificationRepository.deleteById(id)
    }

    override fun updateTitle(notificationId: String, newTitle: String): Notification {
        return findById(notificationId)
            .toMutable()
            .apply {
            title = newTitle
        }.save()

    }

    override fun updateContent(notificationId: String, newContent: String): Notification {
        return findById(notificationId)
            .toMutable()
            .apply {
            content = newContent
        }.save()
    }

    override fun update(notificationId: String, request: NotificationManager.UpdateRequest): Notification {
        return findById(notificationId)
            .toMutable()
            .apply {
            request.title
                ?.let { title =it }
            request.content
                ?.let { content = request.content }
            request.publishedAt
                ?.let { publishedAt = request.publishedAt }
        }.save()
    }

    override fun activeOff(notificationId: String): Notification {
        return findById(notificationId)
            .toMutable()
            .apply {
                active = false
            }.save()
    }

    override fun activeOn(notificationId: String): Notification {
        return findById(notificationId)
            .toMutable()
            .apply {
            active = true
        }.save()
    }

    override fun updatePublishedAt(notificationId: String, publishedTime: Instant): Notification {

        return findById(notificationId)
            .toMutable()
            .apply {
            publishedAt = publishedTime
        }.save()
    }

    override fun findDisplayNotification(appId: String): List<Notification> {

        return notificationRepository.findDisplayNotificationByApp(appId)
    }

    private fun Notification.toMutable(): MutableNotification{
        return MutableNotificationImpl(this)
    }


    private fun Notification.save(): Notification = notificationRepository.save(this)

}