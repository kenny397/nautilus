package com.jun.nautilus.domain

import java.time.Instant

/**
 * 공지사항 서비스
 * 공지사항 생성, 조회, 수정, 삭제 가능을 담당한다
 */
interface NotificationManager {

    /**
     * 공지사항 생성
     *
     * TODO 시간 타입 가나다라. Instant, unix epoch
     */
    fun create(title: String, content: String, publishedAt: Instant, app: App): Notification

    /**
     * 공지사항 단일 조회
     */
    @Throws(NoSuchNotificationException::class)
    fun findById(id: String): Notification

    /**
     * 공지사항 삭제
     */
    fun remove( id: String)

    /**
     * 공지사항 제목 수정
     */

    fun updateTitle( notificationId: String, newTitle: String): Notification

    /**
     * 공지사항 내용 수정
     */

    fun updateContent(notificationId: String, newContent: String): Notification



    fun update(notificationId: String, request: UpdateRequest): Notification


    /**
     * 공지사항 노출 중지
     */
    fun activeOff(notificationId: String): Notification

    /**
     * 공지사항 노출 시작
     */

    fun activeOn(notificationId: String): Notification

    /**
     * 공지사항 노출 시간 변경
     */

    fun updatePublishedAt( notificationId: String, publishedTime: Instant): Notification

    /**
     * 앱에 등록된 공지사항 조회
     */
    fun findDisplayNotification(appId: String): List<Notification>


    data class UpdateRequest(
        val title: String? = null,
        val content: String? = null,
        val publishedAt: Instant? = null
    )
}
