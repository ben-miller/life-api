package com.example.life

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LifeApiApplication

suspend fun main(args: Array<String>) {
	runApplication<LifeApiApplication>(*args)
}
