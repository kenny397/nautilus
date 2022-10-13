package com.jun.nautilus.server.jpa.entity

import com.jun.nautilus.domain.Notification
import com.jun.nautilus.domain.impl.NotificationImpl
import java.time.Instant
import javax.persistence.*


@Entity(name= "notifications")
class NotificationEntity(

    @Id
    @Column(name = "notification_id")
    val id: String,

    val title: String,
    val content: String,
    val createdAt: Instant,
    val publishedAt: Instant,
    val active: Boolean,

    @ManyToOne
    @JoinColumn(name = "app_id")
    val app: AppEntity,
){
    fun toModel(): Notification{
        return NotificationImpl(
            id = id,
            title = title,
            content = content,
            publishedAt = publishedAt,
            app = app.toModel()
        )
    }
    companion object {
        fun from(notification: Notification): NotificationEntity {

        return NotificationEntity(
            id = notification.id,
            title = notification.title,
            content = notification.content,
            createdAt = notification.createdAt,
            publishedAt = notification.publishedAt,
            active = notification.active,
            app = AppEntity.from(notification.app)
            )
        }
    }
}