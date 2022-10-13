package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.*


class AppManagerImpl(
    private val appIdGenerator: StringIdGenerator,
    private val appRepository: AppRepository,
): AppManager {


    override fun create(name: String, owner: User): com.jun.nautilus.domain.App {
        val generatedId = appIdGenerator.generate()
        return appRepository.save(AppImpl(generatedId, name, setOf(owner)))
    }

    override fun updateName( appId: String, newName: String): com.jun.nautilus.domain.App {
        return findById(appId)
            .toMutable()
            .apply { name = newName }
            .save()
    }

    override fun findById(id: String): com.jun.nautilus.domain.App {
        return appRepository.findById(id)
            ?.let { it }
            ?: throw NoSuchAppException(id + "is not exist")
    }

    override fun remove(id: String) {
        appRepository.deleteById(id)
    }

    override fun addOwner( appId: String , newOwner: User): com.jun.nautilus.domain.App {
        return findById(appId)
            .toMutable()
            .apply { owners.add(newOwner) }
            .save()
    }

    override fun findByUser(user: User): List<com.jun.nautilus.domain.App> {
        return appRepository.findByUser(user)
    }
    private fun com.jun.nautilus.domain.App.toMutable(): MutableApp {
        return MutableAppImpl(this)
    }

    private fun com.jun.nautilus.domain.App.save(): com.jun.nautilus.domain.App = appRepository.save(this)


}
