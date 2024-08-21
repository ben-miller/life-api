package com.example.life.datasource

import com.example.life.OrgMode
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class OrgModeDataSource(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableDataSource<OrgMode>(
    redisService,
    "source.orgMode",
    OrgMode::class.java
) {
    override suspend fun fetch(): OrgMode {
        return utilityService.getOrgModeMetrics().let {
            OrgMode(it.inboxesCount, it.inboxTotalItems)
        }
    }

    override suspend fun save(value: OrgMode) {
        TODO("Not yet implemented")
    }
}
