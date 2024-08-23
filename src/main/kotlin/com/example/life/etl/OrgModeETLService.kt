package com.example.life.etl

import com.example.life.model.OrgModeDataSample
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class OrgModeETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<OrgModeDataSample>(
    redisService,
    "orgMode",
    OrgModeDataSample::class.java
) {
    override suspend fun extract(): OrgModeDataSample {
        return utilityService.getOrgModeMetrics().let {
            OrgModeDataSample(it.inboxesCount, it.inboxTotalItems)
        }
    }

    override suspend fun load(value: OrgModeDataSample) {
    }
}
