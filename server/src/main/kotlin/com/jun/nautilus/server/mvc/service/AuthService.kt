package com.jun.nautilus.server.mvc.service

import com.jun.nautilus.auth.AuthManager
import com.jun.nautilus.auth.Authenticator
import com.jun.nautilus.domain.UserService
import com.jun.nautilus.server.mvc.controller.LoginRequest
import com.jun.nautilus.server.mvc.controller.RegisterRequest
import com.jun.nautilus.server.mvc.controller.view.AuthInfo
import com.jun.nautilus.server.mvc.security.JwtAuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService (
    private val authenticator: Authenticator,
    private val authManager: AuthManager,
    private val userService: UserService,
    private val jwtAuthenticationProvider: JwtAuthenticationProvider
)
{

    @Transactional(readOnly = true)
    fun login(loginRequest: LoginRequest): AuthInfo {
        if(authenticator.authenticate(loginRequest.email,loginRequest.password)==Authenticator.Result.Success){
            val userId = userService.findByEmail(loginRequest.email).id
            val accessToken= jwtAuthenticationProvider.createAccessToken( userId )
            return AuthInfo(userId,accessToken)
        }else{
            throw UsernameNotFoundException("올바르지 않는 입력입니다")
        }

    }

    @Transactional
    fun register(registerRequest: RegisterRequest){
        val id =userService.create(registerRequest.name,registerRequest.email).id
        authManager.create(id,registerRequest.email,registerRequest.password)


    }


}