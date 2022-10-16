package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.*

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
        val emailRegex = ("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        return emailRegex.toPattern().matcher(email).matches()
    }

    private fun exists(email: String): Boolean{
        return userRepository.findByEmail(email)
            ?.let { true }
            ?: false
    }



}