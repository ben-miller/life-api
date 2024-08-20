package com.example.life.datasource

import com.example.life.Youtube
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class YoutubeDataSource(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableDataSource<Youtube>(
    redisService,
    "source.youtube",
    Youtube::class.java
) {
    override suspend fun fetch(): Youtube {
        return utilityService.getYoutubeMetrics().let {
            Youtube(it.likedVideosCount)
        }
    }
}
