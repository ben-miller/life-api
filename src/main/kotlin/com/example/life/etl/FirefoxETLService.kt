package com.example.life.etl

import com.example.life.model.FirefoxDataSample
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class FirefoxETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<FirefoxDataSample>(
    redisService,
    "firefox",
    FirefoxDataSample::class.java
) {
    override suspend fun extract(): FirefoxDataSample {
        return utilityService.getFirefoxMetrics().let {
            FirefoxDataSample(it.bookmarksCount)
        }
    }

    override suspend fun load(lastSample: FirefoxDataSample) {
    }
}
