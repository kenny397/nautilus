package com.jun.nautilus.server.jpa.entity

import com.jun.nautilus.domain.AuthUser
import com.jun.nautilus.domain.impl.AuthUserImpl
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "auth_user")
class AuthUserEntity (
    @Id
    val userId: String,

    val email: String,

    val password: String
        ){

    fun toModel(): AuthUser{
        return AuthUserImpl(
            userId = userId,
            email = email,
            password = password
        )
    }

    companion object{
        fun from(authUser: AuthUser): AuthUserEntity{
            return AuthUserEntity(
                userId = authUser.userId,
                email = authUser.email,
                password = authUser.password
            )
        }
    }
}