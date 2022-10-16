package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.Notification


/**
 * 공지사항 저장소
 */
interface NotificationRepository {

    /**
     * Notification 저장
     */
    fun save(notification: Notification): Notification

    /**
     * Notification 조회
     */
    fun findById(id: String): Notification?

    /**
     * Notification 삭제
     */
    fun deleteById(id: String)
    fun findDisplayNotificationByApp(app: App): List<Notification>

    fun findDisplayNotificationByApp(appId: String): List<Notification>

}