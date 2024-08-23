package com.example.life.etl

import com.example.life.model.TidalDataSample
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class TidalETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<TidalDataSample>(
    redisService,
    "tidal",
    TidalDataSample::class.java
) {
    override suspend fun extract(): TidalDataSample {
        return utilityService.getTidalMetrics().let {
            TidalDataSample(it.uncategorizedTracks)
        }
    }

    override suspend fun load(value: TidalDataSample) {
    }
}
