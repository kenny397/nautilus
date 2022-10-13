package com.jun.nautilus.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NautilusApiApplication

fun main(args: Array<String>) {
    runApplication<NautilusApiApplication>(*args)
}
