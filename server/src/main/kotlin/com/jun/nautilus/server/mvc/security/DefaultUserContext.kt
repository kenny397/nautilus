package com.jun.nautilus.server.mvc.security

import com.jun.nautilus.server.mvc.service.UserContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


@Component
class DefaultUserContext(): UserContext {
    override fun getCurrentUserId(): String {
        return SecurityContextHolder.getContext()
            ?.authentication
            ?.let { it.name }
            ?: throw NoUserContextException()
    }
}
class NoUserContextException: RuntimeException("userContext 가 존재하지 않습니다")