package com.example.life.datasource

import com.example.life.RedisService

abstract class CacheableDataSource<T : Any>(
    private val redisService: RedisService,
    private val cacheKey: String,
    private val type: Class<T>
) : DataSource<T>() {
    suspend fun fetchWithCache(forceRefresh: Boolean = false): T =
        redisService.withCached(cacheKey, type, forceRefresh) {
            fetch()
        }
}
