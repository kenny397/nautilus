package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.domain.AppRepository
import com.jun.nautilus.domain.AppRepositoryTest
import com.jun.nautilus.domain.testhelper.anUser
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class JpaAppRepositoryTest: AppRepositoryTest {
    override lateinit var sut: AppRepository

    @Autowired
    lateinit var appEntityRepository: AppEntityRepository

    @Autowired
    lateinit var ownerEntityRepository: OwnerEntityRepository

    @Autowired
    lateinit var userEntityRepository: UserEntityRepository

    @BeforeEach
    fun setUp(){
        sut = JpaAppRepository(appEntityRepository , ownerEntityRepository)
        val userRepository = JpaUserRepository(userEntityRepository)
        userRepository.save(anUser())
    }

}