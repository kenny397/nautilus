package com.jun.nautilus.server.mvc.config

import com.jun.nautilus.domain.*
import com.jun.nautilus.domain.impl.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@ComponentScan
@Configuration
class DomainConfiguration {


    @Bean
    fun authFactory(): AuthFactory{
        return AuthFactoryImpl()
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
    fun authManager(authFactory: AuthFactory, authRepository: AuthRepository, passwordEncoder: PasswordEncoder): AuthManager{
        return AuthManagerImpl(authFactory,authRepository,passwordEncoder)
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