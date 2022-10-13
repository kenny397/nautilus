package com.jun.nautilus.domain


import com.jun.nautilus.domain.testhelper.anApp
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

interface AppRepositoryTest {

    val sut: AppRepository

    @Test
    fun `새로운 앱을 저장한다`() {
        //given
        val app = anApp()

        //when
        val savedApp = sut.save(app)

        //then
        assertThat(savedApp).isEqualTo(app)
    }

    @Test
    fun `앱 id가 같으면 변경된 앱을 저장한다`(){
        //given
        val id = "dummy.id"
        val app = anApp(id = id, name = "init Name")
        sut.save(app)

        //when
        val updateApp = anApp(id = id, name = "new Name")
        sut.save(updateApp)

        //then
        val foundApp = sut.findById(id)
        assertThat(foundApp!!.name).isEqualTo("new Name")
    }



    @Test
    fun `앱 id로 앱을 단건 조회를 할 수 있다`(){
        //given
        val app = anApp()
        sut.save(app)

        //when
        val foundApp = sut.findById(app.id)

        //then
        assertThat(foundApp).isEqualTo(app)
    }


    @Test
    fun `앱 id로 앱을 삭제할 수 있다`(){
        //given
        val app = anApp()
        sut.save(app)

        //when
        sut.deleteById(app.id)

        //then
        val foundApp = sut.findById(app.id)
        assertThat(foundApp).isNull()
    }

}
