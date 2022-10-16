package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.User

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
    fun findAppsByOwner(user: User): List<App>
}