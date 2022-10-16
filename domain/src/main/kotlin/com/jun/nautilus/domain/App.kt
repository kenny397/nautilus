package com.jun.nautilus.domain

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
    val owners: Set<User>

    fun isOwner(user: User): Boolean{
        return owners.find { it == user }
            ?.let {true}
            ?:false
    }
    fun isOwner(userId: String): Boolean{
        return owners.find { it.id == userId }
            ?.let {true}
            ?:false
    }

    abstract class Base : App {
      final override fun hashCode(): Int {
            return id.hashCode()
      }
      final override fun equals(other: Any?): Boolean {
          return other is App
                  && other.id == id
        }
    }
}