package com.jun.nautilus.server.mvc.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

import org.springframework.web.bind.annotation.ResponseBody


@Controller
class WebController : ErrorController {
    @GetMapping("/", "/error")
    fun index(): String {
        return "index.html"
    }

    @ResponseBody
    @GetMapping("/notification/{notificationId}")
    fun findNotificationUrl(@PathVariable notificationId: String):String{

        return "localhost:8080/view/notificationinfo/"+notificationId
    }

    @ResponseBody
    @GetMapping("/notification/list/{appId}")
    fun findNotificationListUrl(@PathVariable appId: String):String{

        return "localhost:8080/view/notification/"+appId
    }
}