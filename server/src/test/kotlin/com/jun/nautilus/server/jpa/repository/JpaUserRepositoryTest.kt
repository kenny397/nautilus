package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.domain.UserRepository
import com.jun.nautilus.domain.UserRepositoryTest
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class JpaUserRepositoryTest : UserRepositoryTest {
    override lateinit var sut: UserRepository

    @Autowired
    lateinit var userEntityRepository: UserEntityRepository

    @BeforeEach
    fun setUp(){
        sut = JpaUserRepository(userEntityRepository)
    }
}