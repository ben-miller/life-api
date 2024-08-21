package com.example.life.datasource

import com.example.life.Tidal
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class TidalETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<Tidal>(
    redisService,
    "source.tidal",
    Tidal::class.java
) {
    override suspend fun extract(): Tidal {
        return utilityService.getTidalMetrics().let {
            Tidal(it.uncategorizedTracks)
        }
    }

    override suspend fun load(value: Tidal) {
        TODO("Not yet implemented")
    }
}
