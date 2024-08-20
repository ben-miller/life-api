package com.example.life.datasource

import com.example.life.Firefox
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class FirefoxDataSource(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableDataSource<Firefox>(
    redisService,
    "source.firefox",
    Firefox::class.java
) {
    override suspend fun fetch(): Firefox {
        return utilityService.getFirefoxMetrics().let {
            Firefox(it.bookmarksCount)
        }
    }
}
