package com.example.life.etl

import com.example.life.service.RedisService

abstract class CacheableETLService<T : Any>(
    private val redisService: RedisService,
    private val cacheKey: String,
    private val type: Class<T>
) : ETLService<T>() {
    suspend fun fetchWithCache(forceRefresh: Boolean = false): T =
        redisService.withCached(cacheKey, type, forceRefresh) {
            extract()
        }
}
