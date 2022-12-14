package com.jun.nautilus.server.jpa.entity

import javax.persistence.*


@Entity(name = "owners")
class OwnerEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,


    val appId: String,

){
    override fun equals(other: Any?): Boolean {
        return other is OwnerEntity
                && other.user.id == user.id
                && other.appId == appId
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}