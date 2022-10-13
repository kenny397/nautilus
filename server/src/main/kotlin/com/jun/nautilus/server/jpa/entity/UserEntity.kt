package com.jun.nautilus.server.jpa.entity

import com.jun.nautilus.domain.User
import com.jun.nautilus.domain.impl.UserImpl

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity(name = "users")
class UserEntity (
    @Id
    @Column(name = "user_id")
    val id: String,
    val name: String,
    val email: String,
){
    fun toModel(): User {
        return UserImpl(
            id = id,
            name = name,
            email = email
        )
    }
    companion object{
        fun from(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                name = user.name,
                email = user.email
            )
        }
    }
    override fun equals(other: Any?): Boolean {
        return other is UserEntity
                && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
