package com.jun.nautilus.server.mvc.controller.view

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.Notification
import com.jun.nautilus.domain.User
import java.time.Instant



data class UserSimpleInfo(
    val name: String
){
    companion object{
        fun from(user: User): UserSimpleInfo =
            UserSimpleInfo(
                name = user.name
            )
    }
}


data class AppInfo(
    val appId: String,
    val name: String,
    val ownerCount: Int
){
    companion object{
        fun from(app: com.jun.nautilus.domain.App): AppInfo =
            AppInfo(
                appId = app.id,
                name = app.name,
                ownerCount = app.owners.size
            )
    }
}

data class AppSimpleInfo(
    val appId: String,
    val name: String,
    val owners: List<UserSimpleInfo>
){
    companion object{
        fun from(app: com.jun.nautilus.domain.App): AppSimpleInfo =
            AppSimpleInfo(
                appId = app.id,
                name = app.name,
                owners = app.owners.map { UserSimpleInfo.from(it) }
            )
    }
}

data class NotificationInfo(
    val notificationId: String,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val publishedAt: Instant
){
    companion object{
        fun from(notification: Notification): NotificationInfo =
            NotificationInfo(
                notificationId = notification.id,
                title = notification.title,
                content = notification.content,
                createdAt = notification.createdAt,
                publishedAt = notification.publishedAt
            )
    }
}

data class NotificationSimpleInfo(
    val notificationId: String,
    val title: String,
    val publishedAt: Instant
){
    companion object{
        fun from(notification: Notification): NotificationSimpleInfo =
            NotificationSimpleInfo(
                notificationId = notification.id,
                title = notification.title,
                publishedAt = notification.publishedAt
            )
    }
}

data class AuthInfo(
    val userId: String,
    val accessToken: String
){
}