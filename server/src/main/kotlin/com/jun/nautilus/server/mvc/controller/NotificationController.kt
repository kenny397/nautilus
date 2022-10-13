package com.jun.nautilus.server.mvc.controller

import com.jun.nautilus.domain.Notification
import com.jun.nautilus.server.mvc.controller.view.NotificationInfo
import com.jun.nautilus.server.mvc.controller.view.NotificationSimpleInfo
import com.jun.nautilus.server.mvc.service.NotificationService
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
import java.time.Instant


@RestController
@RequestMapping("/api/notifications")
class NotificationController (private val notificationService: NotificationService){

    @PostMapping("")
    fun create(@RequestBody notificationCreateRequest: NotificationCreateRequest): ResponseEntity<NotificationInfo>{
        return notificationService.create(notificationCreateRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @DeleteMapping("/{notificationId}")
    fun delete(@PathVariable notificationId: String): ResponseEntity<Unit>{
        notificationService.delete(notificationId)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PutMapping("/{notificationId}/title")
    fun updateTitle(@PathVariable notificationId: String, @RequestParam newTitle: String): ResponseEntity<NotificationInfo>{
        return notificationService.updateTitle(notificationId,newTitle)
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    }

    @PutMapping("/{notificationId}/content")
    fun updateContent(@PathVariable notificationId: String, @RequestParam newContent: String): ResponseEntity<NotificationInfo>{
        return notificationService.updateContent(notificationId,newContent)
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    }

    @PutMapping("/{notificationId}/publish")
    fun updatePublishTime(@PathVariable notificationId: String, @RequestParam newTime: String): ResponseEntity<NotificationInfo>{
        return notificationService.updatePublishTime(notificationId,newTime)
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    }

    @PutMapping("/{notificationId}/inactive")
    fun inactive(@PathVariable notificationId: String): ResponseEntity<NotificationInfo>{
        return notificationService.inactive(notificationId)
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @PutMapping("/{notificationId}/active")
    fun active(@PathVariable notificationId: String): ResponseEntity<NotificationInfo>{
        return notificationService.active(notificationId)
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @GetMapping("/list/{appId}")
    fun findDisplayNotification(@PathVariable appId: String): ResponseEntity<List<NotificationSimpleInfo>>{

        return notificationService.findDisplayNotificationByApp(appId)
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }
    }

    @GetMapping("/{notificationId}")
    fun findById(@PathVariable notificationId: String): ResponseEntity<NotificationInfo>{
        return notificationService.findById(notificationId)
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }
    }
}

data class NotificationCreateRequest(
    val title: String ,
    val content: String,
    val publishedAt: String,
    val appId: String
)

