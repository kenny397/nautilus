package com.jun.nautilus.server.mvc.service

import com.jun.nautilus.domain.AppManager
import com.jun.nautilus.domain.UserService
import com.jun.nautilus.domain.testhelper.anApp
import com.jun.nautilus.domain.testhelper.anUser
import com.jun.nautilus.server.mvc.error.NoAppOwnerException
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class AppServiceTest{

    @MockK
    lateinit var appManager: AppManager

    @MockK
    lateinit var userService: UserService

    @MockK
    lateinit var userContext: UserContext

    lateinit var sut: AppService

    @BeforeEach
    fun setUp() {
        every { userContext.getCurrentUserId() }returns "userTestId1"
        sut = AppService(appManager, userService,userContext)
    }

    @Test
    fun `app의 오너가 아닌사람이 이름변경을 요청하면 NoOwnerException을 던진다`() {
        //given

        val appId ="appTestId"
        every { appManager.findById(appId) } returns anApp(owners = setOf(anUser(id = "userTestId2")))



        assertThrows<NoAppOwnerException> { sut.updateName(appId,"useName") }


    }

    @Test
    fun `app의 오너가 아닌사람이 관리자 추가를 요청하면 NoOwnerException을 던진다`() {
        //given
        val userId = userContext.getCurrentUserId()
        val appId ="appTestId"
        every { appManager.findById(appId) } returns anApp(owners = setOf(anUser(id = "userTestId2")))



        assertThrows<NoAppOwnerException> { sut.addOwner(appId,userId) }


    }
    @Test
    fun `app의 오너가 아닌사람이 삭제를 요청하면 NoOwnerException을 던진다`() {
        //give
        val appId ="appTestId"
        every { appManager.findById(appId) } returns anApp(owners = setOf(anUser(id = "userTestId2")))



        assertThrows<NoAppOwnerException> { sut.delete(appId) }


    }

}