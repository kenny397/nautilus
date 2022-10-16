package com.jun.nautilus.server.jpa.entity

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.impl.AppImpl
import javax.persistence.*

@Entity(name = "apps")
class AppEntity (
    @Id
    @Column(name = "app_id")
    val id: String,

    var name: String,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "appId")
    val owners: MutableSet<OwnerEntity> = mutableSetOf()

){
    fun toModel(): com.jun.nautilus.domain.App {
        return AppImpl(
            id = id,
            name = name,
            owners = owners.map { it.user.toModel() }.toSet()
        )
    }
    companion object{
        fun from(app: com.jun.nautilus.domain.App): AppEntity {
            return AppEntity(
                id = app.id,
                name = app.name,
                owners = app.owners.map { OwnerEntity(0, UserEntity.from(it),app.id) }.toMutableSet()
            )
        }
    }

}