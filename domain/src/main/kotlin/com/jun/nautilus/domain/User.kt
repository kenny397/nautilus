package com.jun.nautilus.domain


/**
 * 유저 도메인.
 *
 *
 **/
interface User {

    /**
     * 유저 식별자
     *
     * UserIdGenerator로 id를 생성 할 수 있다
     */
    val id: String

    /**
     * 유저 이름
     */
    val name: String

    //vaild 중복체크가 필요
    val email: String



    abstract class Base : User {
        final override fun hashCode(): Int {
            return id.hashCode()
        }

        final override fun equals(other: Any?): Boolean {
            return other is User
                    && other.id == id
        }
    }


}