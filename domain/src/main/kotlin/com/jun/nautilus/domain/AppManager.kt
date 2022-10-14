package com.jun.nautilus.domain

/**
 * 앱 서비스
 * 앱 생성, 조회, 변경, 삭제 기능을 담당한다
 * 앱 관리자 추가기능을 담당
 */
interface AppManager {

    /**
     * 앱 생성
     */
    fun create(name: String, owner: User): App

    /**
     * 앱 이름 변경
     */
    fun updateName(appId: String, newName: String): App

    /**
     * 앱 단일 조회
     */
    @Throws(NoSuchAppException::class)
    fun findById(id: String): App

    /**
     * 앱 삭제
     */
    fun remove(appId: String)

    /**
     * 앱 관리자 추가
     */

    fun addOwner(appId: String, newOwner: User): App

    fun findByUser(user: User): List<App>

}

