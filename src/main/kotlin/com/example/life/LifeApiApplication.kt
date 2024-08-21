package com.example.life

import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class LifeApiApplication

fun main(args: Array<String>): Unit = runBlocking {
	runApplication<LifeApiApplication>(*args)
}
