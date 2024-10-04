package com.example.life.etl

import com.example.life.model.OrgModeDataSample
import com.example.life.model.OrgModeTask
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
            OrgModeDataSample(
                it.inboxTotalItems,
                it.totalBugs,
                it.projectTasksList.map { OrgModeTask(it.task, it.state) })
        }
    }

    override suspend fun load(value: OrgModeDataSample) {
    }
}
