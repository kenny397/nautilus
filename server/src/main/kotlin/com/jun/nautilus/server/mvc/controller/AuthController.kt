package com.jun.nautilus.server.mvc.controller

import com.jun.nautilus.server.mvc.controller.view.AuthInfo
import com.jun.nautilus.server.mvc.security.JwtTokenManager
import com.jun.nautilus.server.mvc.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    @Autowired
    private val authService: AuthService,
) {

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<String>{
        return authService.register(registerRequest)
            .let {   ResponseEntity.status(HttpStatus.CREATED).body(it+"아이디로 생성되셨습니다")}
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<AuthInfo>{
        return authService.login(loginRequest)
            .let { ResponseEntity.status(HttpStatus.OK).body(it)}
    }

    @PostMapping("/reissueToken")
    fun reissueToken(@RequestParam("refreshToken") refreshToken: String): ResponseEntity<AuthInfo>{
        return authService.reissueToken(refreshToken)
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }

    }


}

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)