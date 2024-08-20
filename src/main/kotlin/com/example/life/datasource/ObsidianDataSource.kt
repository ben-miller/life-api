package com.example.life.datasource

import com.example.life.Obsidian
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class ObsidianDataSource(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableDataSource<Obsidian>(
    redisService,
    "source.obsidian",
    Obsidian::class.java
) {
    override suspend fun fetch(): Obsidian {
        return utilityService.getObsidianMetrics().let {
            Obsidian(it.inboxesCount, it.inboxTotalItems)
        }
    }
}
