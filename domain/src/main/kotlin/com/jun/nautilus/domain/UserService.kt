package com.jun.nautilus.domain


/**
 * 유저 서비스는 유저 생성, 조회, 삭제 기능을 제공한다
 */
interface UserService {

    /**
     * 유저 생성
     */
    @Throws(IllegalArgumentException::class,
            EmailExistException::class)
    fun create(name: String, email: String): User

    /**
     * 유저 단일 조회
     */
    @Throws(NoSuchUserException::class)
    fun findById(userId: String): User

    /**
     * 유저 삭제
     */
    fun remove(userId: String)

    fun findByEmail(email: String): User
}