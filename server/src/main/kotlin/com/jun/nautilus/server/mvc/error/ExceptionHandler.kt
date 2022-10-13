package com.jun.nautilus.server.mvc.error

import com.jun.nautilus.domain.NoAppOwnerException
import com.jun.nautilus.domain.NoSuchAppException
import com.jun.nautilus.domain.NoSuchNotificationException
import com.jun.nautilus.domain.NoSuchUserException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(
        value = [
            NoSuchAppException::class,
            NoSuchUserException::class,
            NoSuchNotificationException::class,
            NoAppOwnerException::class,
            UsernameNotFoundException::class
        ]
    )
    fun handleBadRequest(ex: RuntimeException): ResponseEntity<ErrorInfo> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorInfo(
                    message = ex.message
                )
            )
    }
}

data class ErrorInfo(
    val message: String? = null
)