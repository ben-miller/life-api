package com.example.life.etl

import com.example.life.model.Youtube
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class YoutubeETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<Youtube>(
    redisService,
    "source.youtube",
    Youtube::class.java
) {
    override suspend fun extract(): Youtube {
        return utilityService.getYoutubeMetrics().let {
            Youtube(it.likedVideosCount)
        }
    }

    override suspend fun load(value: Youtube) {
        TODO("Not yet implemented")
    }
}
