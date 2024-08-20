package com.example.life.datasource

import com.example.life.Trello
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class TrelloDataSource(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableDataSource<Trello>(
    redisService,
    "source.trello",
    Trello::class.java
) {
    override suspend fun fetch(): Trello {
        return utilityService.getTrelloMetrics().let {
            Trello(it.inboxSize)
        }
    }
}
