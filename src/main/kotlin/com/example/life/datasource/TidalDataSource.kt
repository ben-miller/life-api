package com.example.life.datasource

import com.example.life.Tidal
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class TidalDataSource(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableDataSource<Tidal>(
    redisService,
    "source.tidal",
    Tidal::class.java
) {
    override suspend fun fetch(): Tidal {
        return utilityService.getTidalMetrics().let {
            Tidal(it.uncategorizedTracks)
        }
    }
}
