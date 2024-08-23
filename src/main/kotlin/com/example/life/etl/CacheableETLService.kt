package com.example.life.etl

import com.example.life.service.RedisService

abstract class CacheableETLService<T : Any>(
    private val redisService: RedisService,
    sourceName: String,
    private val type: Class<T>
) : ETLService<T>(sourceName) {
    suspend fun fetchWithCache(forceRefresh: Boolean = false): T =
        redisService.withCached("source.$sourceName", type, forceRefresh) {
            fetchAndSave()
        }
}
