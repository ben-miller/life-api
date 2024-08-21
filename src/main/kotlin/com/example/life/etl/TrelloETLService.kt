package com.example.life.etl

import com.example.life.service.RedisService
import com.example.life.model.Trello
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class TrelloETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<Trello>(
    redisService,
    "source.trello",
    Trello::class.java
) {
    override suspend fun extract(): Trello {
        return utilityService.getTrelloMetrics().let {
            Trello(it.inboxSize)
        }
    }

    override suspend fun load(value: Trello) {
        TODO("Not yet implemented")
    }
}
