package com.example.life.etl

import com.example.life.service.RedisService
import com.example.life.model.TrelloDataSample
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class TrelloETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<TrelloDataSample>(
    redisService,
    "trello",
    TrelloDataSample::class.java
) {
    override suspend fun extract(): TrelloDataSample {
        return utilityService.getTrelloMetrics().let {
            TrelloDataSample(it.inboxSize)
        }
    }

    override suspend fun load(value: TrelloDataSample) {
    }
}
