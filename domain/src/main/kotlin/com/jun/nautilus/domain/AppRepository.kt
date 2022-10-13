package com.jun.nautilus.domain

/**
 * 앱 저장소
 */
interface AppRepository {

    /**
     * 앱 저장
     */
    fun save(app: com.jun.nautilus.domain.App): com.jun.nautilus.domain.App

    /**
     * 앱 단일 조회
     */
    fun findById(appId: String): com.jun.nautilus.domain.App?


    /**
     * 앱 삭제
     */
    fun deleteById(appId: String)
    fun findByUser(user: User): List<com.jun.nautilus.domain.App>
}