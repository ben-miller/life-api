package com.example.life

import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * Life API - Personal data aggregation and metrics tracking service.
 *
 * This application provides a GraphQL API for querying and tracking personal metrics
 * from various data sources including productivity tools, media consumption, and more.
 *
 * Key features:
 * - GraphQL API for flexible data querying
 * - Scheduled ETL jobs for data synchronization
 * - Reactive architecture with Kotlin coroutines
 * - Redis caching for performance
 * - PostgreSQL for persistent storage
 */
@SpringBootApplication
@EnableScheduling
class LifeApiApplication

fun main(args: Array<String>): Unit = runBlocking {
	runApplication<LifeApiApplication>(*args)
}
