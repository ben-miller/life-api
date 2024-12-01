package com.example.life.etl

import com.example.life.model.FirefoxDataSample
import com.example.life.service.RedisService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class FirefoxETLService(
    redisService: RedisService,
    @Value("\${local_data.firefox_dir}") private val firefoxDir: String
) : CacheableETLService<FirefoxDataSample>(
    redisService,
    "firefox",
    FirefoxDataSample::class.java
) {
    override suspend fun extract(): FirefoxDataSample {
        System.out.println("firefox dir: ${this.firefoxDir}")
        // TODO Use real data
        return FirefoxDataSample(22)
    }

    override suspend fun load(lastSample: FirefoxDataSample) {
    }
}
