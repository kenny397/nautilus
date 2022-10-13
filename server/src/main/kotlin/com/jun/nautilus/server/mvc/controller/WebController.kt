package com.jun.nautilus.server.mvc.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class WebController : ErrorController {
    @GetMapping("/", "/error")
    fun index(): String {
        return "index.html"
    }

}