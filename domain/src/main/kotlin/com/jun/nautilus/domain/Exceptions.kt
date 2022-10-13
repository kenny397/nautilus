package com.jun.nautilus.domain

open class UserException(message: String?, cause: Throwable?): RuntimeException(message, cause)

class EmailExistException(message: String?, cause: Throwable? = null): UserException(message, cause)

class NoSuchUserException(message: String?, cause: Throwable? = null): UserException(message, cause)

open class AppException(message: String?, cause: Throwable?): RuntimeException(message, cause)

class NoSuchAppException(message: String?, cause: Throwable? = null): AppException(message, cause)

class NoAppOwnerException(message: String?,cause: Throwable? = null): AppException(message,cause)

open class NotificationException(message: String?, cause: Throwable?): RuntimeException(message, cause)

class NoSuchNotificationException(message: String?, cause: Throwable? = null): NotificationException(message, cause)


