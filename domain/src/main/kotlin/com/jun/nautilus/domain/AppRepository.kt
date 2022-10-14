package com.jun.nautilus.domain

/**
 * 앱 저장소
 */
interface AppRepository {

    /**
     * 앱 저장
     */
    fun save(app: App): App

    /**
     * 앱 단일 조회
     */
    fun findById(appId: String): App?


    /**
     * 앱 삭제
     */
    fun deleteById(appId: String)
    fun findByUser(user: User): List<App>
}