package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.server.jpa.entity.NotificationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.Instant

interface NotificationEntityRepository: JpaRepository<NotificationEntity,String> {

    @Query("select noti from notifications noti  join noti.app app where app.id = :appId and noti.active = :active and noti.publishedAt <= :time")
    fun findDisplayNotificationByApp(appId: String,active: Boolean,time: Instant): List<NotificationEntity>
}