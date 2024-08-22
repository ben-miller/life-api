package com.example.life.etl

import com.example.life.model.Tidal
import com.example.life.repository.DataSourceRepository
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class TidalETLService(
    private val utilityService: UtilityService,
    dataSourceRepository: DataSourceRepository,
    redisService: RedisService
) : CacheableETLService<Tidal>(
    redisService,
    dataSourceRepository,
    "tidal",
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
