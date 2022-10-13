package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.*
import java.util.regex.Pattern

class UserServiceImpl(
    private val userIdGenerator: StringIdGenerator,
    private val userRepository: UserRepository
    ): UserService {


    override fun create(name: String, email: String): User {
        if(!emailValid(email)) throw IllegalArgumentException("email is  ")
        if(exists(email)) throw EmailExistException("email is exist")
        return userRepository.save(UserImpl(userIdGenerator.generate(),name,email))
    }


    override fun findById(userId: String): User {
        return userRepository.findById(userId)
            ?. let { it }
            ?: throw NoSuchUserException(userId +"is not User")
    }

    override fun remove(userId: String) {
        userRepository.deleteById(userId)
    }

    override fun findByEmail(email: String): User {
        return userRepository.findByEmail(email)
            ?.let { it }
            ?:throw NoSuchUserException(email+"is not User")
    }

    private fun emailValid(email: String): Boolean{
        val emailRegex = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return emailRegex.matcher(email).matches()
    }

    private fun exists(email: String): Boolean{
        return userRepository.findByEmail(email)
            ?.let { true }
            ?: false
    }



}