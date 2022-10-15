package com.jun.nautilus.server.mvc.config

import com.jun.nautilus.domain.*
import com.jun.nautilus.domain.impl.*
import com.jun.nautilus.auth.*
import com.jun.nautilus.auth.impl.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@ComponentScan
@Configuration
class DomainConfiguration {


    @Bean
    fun authenticator(authRepository: AuthRepository, passwordEncoder: PasswordEncoder): Authenticator{
        return AuthenticatorImpl(authRepository,passwordEncoder)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return Sha256Encoder()
    }

    @Bean
    fun stringIdGenerator(): StringIdGenerator{
        return UUIDGenerator()
    }


    @Bean
    fun authManager(authRepository: AuthRepository, passwordEncoder: PasswordEncoder): AuthManager{
        return AuthManagerImpl(authRepository,passwordEncoder)
    }

    @Bean
    fun userService(stringIdGenerator: StringIdGenerator, userRepository: UserRepository):UserService{
        return UserServiceImpl(stringIdGenerator,userRepository)
    }

    @Bean
    fun notificationManager(stringIdGenerator: StringIdGenerator, notificationRepository: NotificationRepository): NotificationManager{
        return NotificationManagerImpl(stringIdGenerator,notificationRepository)
    }

    @Bean
    fun appManager(stringIdGenerator: StringIdGenerator, appRepository: AppRepository): AppManager{
        return AppManagerImpl(stringIdGenerator,appRepository)
    }


}