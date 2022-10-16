package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.User

class UserImpl(
    override val id: String,
    override val name: String,
    override val email: String) : User.Base(){

    }