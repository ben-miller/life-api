package com.example.life.etl

import com.example.life.model.YoutubeDataSample
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class YoutubeETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<YoutubeDataSample>(
    redisService,
    "youtube",
    YoutubeDataSample::class.java
) {
    override suspend fun extract(): YoutubeDataSample {
        return utilityService.getYoutubeMetrics().let {
            YoutubeDataSample(it.likedVideosCount)
        }
    }

    override suspend fun load(value: YoutubeDataSample) {
    }
}
