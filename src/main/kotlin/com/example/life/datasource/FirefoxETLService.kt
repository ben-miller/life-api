package com.example.life.datasource

import com.example.life.Firefox
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class FirefoxETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<Firefox>(
    redisService,
    "source.firefox",
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
