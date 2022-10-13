package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.StringIdGenerator
import java.util.UUID

class UUIDGenerator: StringIdGenerator {

    override fun generate(): String {
        return UUID.randomUUID().toString()
    }
}