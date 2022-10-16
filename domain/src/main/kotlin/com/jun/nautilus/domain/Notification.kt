package com.jun.nautilus.domain

import java.time.Instant


/**
 * 공지 도메인
 */
interface Notification {

    /**
     * 공지 식별자
     *
     * NotificationIdGenerator로 id를 생성 할 수 있다
     */
    val id: String

    /**
     * 공지 제목
     */
    val title: String

    /**
     * 공지 내용
     */
    val content: String

    /**
     * 공지 작성 시간
     */
    val createdAt: Instant

    /**
     * 공지 노출 시작 시간
     */
    val publishedAt: Instant


    // publishedAt?


    /**
     * 노출 여부 확인
     */
    val active: Boolean
    // hidden:


    /**
     * 공지사항이 작성된 앱
     */
    val app: App


    fun display(): Boolean{
        if(publishedAt.isBefore(Instant.now())&&active)return true
        return false
    }

    abstract class Base: Notification {

        final override fun hashCode(): Int {
            return id.hashCode()
        }

        final override fun equals(other: Any?): Boolean {
            return other is Notification
                    && other.id == id
        }
    }


}