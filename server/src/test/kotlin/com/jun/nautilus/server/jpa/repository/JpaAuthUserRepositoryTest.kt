package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.domain.impl.AuthRepository
import com.jun.nautilus.domain.AuthRepositoryTest
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class JpaAuthUserRepositoryTest: AuthRepositoryTest{
    override lateinit var sut: AuthRepository

    @Autowired
    lateinit var authUserEntityRepository: AuthUserEntityRepository

    @BeforeEach
    fun setUp(){
        sut = JpaAuthUserRepository(authUserEntityRepository)
    }
}