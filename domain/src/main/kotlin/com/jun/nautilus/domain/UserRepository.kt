package com.jun.nautilus.domain


/**
 * 유저 저장소
 */
interface UserRepository {

    /**
     * 유저 저장
     */
    fun save(user: User): User

    /**
     * 유저 단일 조회
     */
    fun findById(userId: String): User?


    /**
     * 유저 삭제
     */
    fun deleteById(userId: String)
    fun findByEmail(email: String): User?

}