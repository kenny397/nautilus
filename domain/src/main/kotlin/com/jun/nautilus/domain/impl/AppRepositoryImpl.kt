package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.AppRepository
import com.jun.nautilus.domain.User

class AppRepositoryImpl: AppRepository {

    private val apps: MutableList<com.jun.nautilus.domain.App> = mutableListOf()

    override fun save(app: com.jun.nautilus.domain.App): com.jun.nautilus.domain.App {
        return findById(app.id)
            ?. let{
                apps.remove(it)
                apps.add(app)
                app
            }
            ?: let{
                apps.add(app)
                app
            }
    }

    override fun findById(appId: String): com.jun.nautilus.domain.App? {
        return apps.find { it.id == appId }
    }

    override fun deleteById(id: String) {
        apps.removeIf { it.id == id }
    }

    override fun findByUser(user: User): List<com.jun.nautilus.domain.App> {
        return apps.filter { it.owners.find { o -> o.id == user.id } !=null }
    }
}