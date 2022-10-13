package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.User

internal interface MutableApp : com.jun.nautilus.domain.App {

    override val id: String

    override var name: String

    override val owners: MutableSet<User>
}

internal class MutableAppImpl(
    override val id: String,
    override var name: String,
    owners: Collection<User>
) : MutableApp, com.jun.nautilus.domain.App.Base() {
    override val owners: MutableSet<User> = mutableSetOf<User>().apply { addAll(owners)}
    constructor(app: com.jun.nautilus.domain.App) :
            this(
                id = app.id,
                name = app.name,
                owners = app.owners
            )

}