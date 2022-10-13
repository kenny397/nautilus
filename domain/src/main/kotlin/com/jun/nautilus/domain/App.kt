package com.jun.nautilus.domain

import java.time.LocalDateTime

/**
 * 앱 도메인
 *
 */
interface App {

    /**
     * 앱 식별자
     *
     * AppIdGenerator로 appId 를 생성 할 수 있다.
     */
    val id: String

    /**
     * 앱 이름
     */
    val name: String

    /**
     * 앱 관리자들
     */
    val owners: Set<com.jun.nautilus.domain.User>

    fun isOwner(user: com.jun.nautilus.domain.User): Boolean{
        return owners.find { it == user }
            ?.let {true}
            ?:false
    }
    fun isOwner(userId: String): Boolean{
        return owners.find { it.id == userId }
            ?.let {true}
            ?:false
    }

    abstract class Base : com.jun.nautilus.domain.App {
      final override fun hashCode(): Int {
            return id.hashCode()
      }
      final override fun equals(other: Any?): Boolean {
          return other is com.jun.nautilus.domain.App
                  && other.id == id
        }
    }
}