package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.User
import com.jun.nautilus.domain.UserRepository


class UserRepositoryImpl: UserRepository {

    private val users: MutableList<User> = mutableListOf()

    override fun save(user: User): User {
        users.add(user)
        return user
    }

    override fun findById(id: String): User? {
        return users.find { it.id == id }
    }


    override fun deleteById(id: String) {
        users.removeIf { it.id == id }
    }

    override fun findByEmail(email: String): User? {
        return users.find {it.email == email}
    }


}