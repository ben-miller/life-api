package com.example.life.datasource

import com.example.life.OrgMode
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class OrgModeETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<OrgMode>(
    redisService,
    "source.orgMode",
    OrgMode::class.java
) {
    override suspend fun extract(): OrgMode {
        return utilityService.getOrgModeMetrics().let {
            OrgMode(it.inboxesCount, it.inboxTotalItems)
        }
    }

    override suspend fun load(value: OrgMode) {
        TODO("Not yet implemented")
    }
}
