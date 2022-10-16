package com.jun.nautilus.server.mvc.controller


import com.jun.nautilus.server.mvc.controller.view.AppInfo
import com.jun.nautilus.server.mvc.controller.view.AppSimpleInfo
import com.jun.nautilus.server.mvc.service.AppService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/apps")
class AppController(private val appService: AppService) {

    @PostMapping("")
    fun create(@RequestBody appCreateRequest: AppCreateRequest): ResponseEntity<AppInfo> {
        return appService.create(appCreateRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @DeleteMapping("/{appId}")
    fun delete(@PathVariable appId: String): ResponseEntity<Unit>{
        appService.delete(appId)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PutMapping("/{appId}/name")
    fun updateName(@PathVariable appId: String, @RequestParam newName: String): ResponseEntity<AppInfo>{
        return appService.updateName(appId, newName)
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @PutMapping("/owner")
    fun addOwner(@RequestParam appId: String, @RequestParam userId: String): ResponseEntity<Unit>{
         appService.addOwner(appId, userId)
         return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/owner/{userId}")
    fun findAppsByOwner(@PathVariable userId: String):ResponseEntity<List<AppSimpleInfo>>{
        return appService.findAppsByOwner(userId)
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }
    }

}

data class AppCreateRequest(
    val name: String,
    val userId: String
)