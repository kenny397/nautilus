package com.jun.nautilus.domain

import com.jun.nautilus.domain.testhelper.anUser

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

interface AppManagerTest {

    val sut: AppManager


    @Test
    fun `앱을 이름과 유저를 가지고 생성한다`() {
        //given
        val user = anUser(name = "kenny.seo")

        //when
        val app = sut.create("testName", user)

        //then
        assertThat(app.name).isEqualTo("testName")
    }

    @Test
    fun `앱 이름을 변경할 수 있다`() {
        //given
        val user = anUser()
        val app = sut.create("testName", user)

        //when
        val newName = "newName"
        val newApp = sut.updateName( app.id, newName)

        //then
        assertThat(newApp.name).isEqualTo(newName)
    }

    @Test
    fun `앱을 삭제 할 수 있다`() {
        //given
        val user = anUser()
        val app = sut.create("testName", user)
        val foundApp = sut.findById(app.id)
        assertThat(app).isEqualTo(foundApp)

        //when
        sut.remove(app.id)

        //then
        assertThrows<NoSuchAppException> { sut.findById(app.id) }
    }

    @Test
    fun `앱 관리자는 앱 관리자를 추가 할 수 있다`() {
        //given
        val user1 = anUser(id = UUID.randomUUID().toString(),name = "taki.ng")
        val app = sut.create("testName", user1)
        assertThat(app.owners).isEqualTo(setOf(user1))

        //when
        val user2 = anUser(id = UUID.randomUUID().toString(), name = "kenny.seo")
        sut.addOwner(app.id, user2)

        //then
        val findApp = sut.findById(app.id)
        assertThat(findApp.owners).isEqualTo(setOf(user1, user2))
    }

    @Test
    fun `유저가 가지고 있는 앱들을 조회할 수 있다`() {
        //given
        val user1 = anUser(id = UUID.randomUUID().toString(),name = "taki.ng")
        val app1 = sut.create("testName1", user1)
        val app2 = sut.create("testName2", user1)

        //when
        val findList=sut.findAppsByOwner(user1)

        //then
        assertThat(findList).isEqualTo(listOf(app1,app2))
    }




}