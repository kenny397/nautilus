package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.domain.impl.NotificationRepository
import com.jun.nautilus.domain.NotificationRepositoryTest
import com.jun.nautilus.domain.testhelper.anApp
import com.jun.nautilus.domain.testhelper.anUser
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class JpaNotificationRepositoryTest: NotificationRepositoryTest {
    override lateinit var sut: NotificationRepository

    @Autowired
    lateinit var notificationEntityRepository: NotificationEntityRepository

    @Autowired
    lateinit var appEntityRepository: AppEntityRepository

    @Autowired
    lateinit var ownerEntityRepository: OwnerEntityRepository

    @Autowired
    lateinit var userEntityRepository: UserEntityRepository

    @BeforeEach
    fun setUp(){
        sut = JpaNotificationRepository(notificationEntityRepository)
        val userRepository = JpaUserRepository(userEntityRepository)
        userRepository.save(anUser())
        val appRepository = JpaAppRepository(appEntityRepository,ownerEntityRepository)
        appRepository.save(anApp())
    }

}