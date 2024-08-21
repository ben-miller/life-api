package com.example.life.etl

import com.example.life.model.Obsidian
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class ObsidianETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<Obsidian>(
    redisService,
    "source.obsidian",
    Obsidian::class.java
) {
    override suspend fun extract(): Obsidian {
        return utilityService.getObsidianMetrics().let {
            Obsidian(it.inboxesCount, it.inboxTotalItems)
        }
    }

    override suspend fun load(value: Obsidian) {
        TODO("Not yet implemented")
    }
}
