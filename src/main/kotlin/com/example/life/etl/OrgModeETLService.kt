package com.example.life.etl

import com.example.life.model.OrgMode
import com.example.life.repository.DataSourceRepository
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class OrgModeETLService(
    private val utilityService: UtilityService,
    dataSourceRepository: DataSourceRepository,
    redisService: RedisService
) : CacheableETLService<OrgMode>(
    redisService,
    dataSourceRepository,
    "orgMode",
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
