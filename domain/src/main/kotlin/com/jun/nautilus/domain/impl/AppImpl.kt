package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.User

class AppImpl(override val id: String,
              override val name: String,
              override val owners: Set<User>) : App.Base() {
}