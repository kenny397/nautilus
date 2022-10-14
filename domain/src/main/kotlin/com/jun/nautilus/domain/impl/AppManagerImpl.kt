package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.*


class AppManagerImpl(
    private val appIdGenerator: StringIdGenerator,
    private val appRepository: AppRepository,
): AppManager {


    override fun create(name: String, owner: User): App {
        val generatedId = appIdGenerator.generate()
        return appRepository.save(AppImpl(generatedId, name, setOf(owner)))
    }

    override fun updateName( appId: String, newName: String): App {
        return findById(appId)
            .toMutable()
            .apply { name = newName }
            .save()
    }

    override fun findById(id: String): App {
        return appRepository.findById(id)
            ?.let { it }
            ?: throw NoSuchAppException(id + "is not exist")
    }

    override fun remove(id: String) {
        appRepository.deleteById(id)
    }

    override fun addOwner( appId: String , newOwner: User): App {
        return findById(appId)
            .toMutable()
            .apply { owners.add(newOwner) }
            .save()
    }

    override fun findByUser(user: User): List<App> {
        return appRepository.findByUser(user)
    }
    private fun App.toMutable(): MutableApp {
        return MutableAppImpl(this)
    }

    private fun App.save(): App = appRepository.save(this)


}
