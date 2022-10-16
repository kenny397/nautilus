package com.jun.nautilus.server.mvc.error

import com.jun.nautilus.domain.AppException


class NoAppOwnerException(message: String?,cause: Throwable? = null): AppException(message,cause)