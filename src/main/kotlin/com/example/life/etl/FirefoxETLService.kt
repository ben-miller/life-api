package com.example.life.etl

import com.example.life.model.Firefox
import com.example.life.repository.DataSourceRepository
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class FirefoxETLService(
    private val utilityService: UtilityService,
    dataSourceRepository: DataSourceRepository,
    redisService: RedisService
) : CacheableETLService<Firefox>(
    redisService,
    dataSourceRepository,
    "firefox",
    Firefox::class.java
) {
    override suspend fun extract(): Firefox {
        return utilityService.getFirefoxMetrics().let {
            Firefox(it.bookmarksCount)
        }
    }

    override suspend fun load(value: Firefox) {
        TODO("Not yet implemented")
    }
}
