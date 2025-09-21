package com.github.dizk.almanakk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AlmanakkApplication

fun main(args: Array<String>) {
    runApplication<AlmanakkApplication>(*args)
}
