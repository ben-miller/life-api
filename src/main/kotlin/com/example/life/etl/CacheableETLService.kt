package com.example.life.etl

import com.example.life.repository.DataSourceRepository
import com.example.life.service.RedisService

abstract class CacheableETLService<T : Any>(
    private val redisService: RedisService,
    dataSourceRepository: DataSourceRepository,
    sourceName: String,
    private val type: Class<T>
) : ETLService<T>(dataSourceRepository, sourceName) {
    suspend fun fetchWithCache(forceRefresh: Boolean = false): T =
        redisService.withCached("source.$sourceName", type, forceRefresh) {
            extract()
        }
}
