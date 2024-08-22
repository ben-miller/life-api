package com.example.life.etl

import com.example.life.model.Airtable
import com.example.life.model.JobSearch
import com.example.life.repository.DataSourceRepository
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service


@Service
class AirtableETLService(
    private val utilityService: UtilityService,
    dataSourceRepository: DataSourceRepository,
    redisService: RedisService
) : CacheableETLService<Airtable>(
    redisService,
    dataSourceRepository,
    "airtable",
    Airtable::class.java
) {
    override suspend fun extract(): Airtable {
        return utilityService.getAirtableJobSearchMetrics().let {
            Airtable(
                JobSearch(
                    ignored_applications = it.ignoredApplications,
                    rejected_applications = it.rejectedApplications,
                    rejected_after_phone_screening = it.rejectedAfterPhoneScreening,
                    rejected_after_technical_screening = it.rejectedAfterTechnicalScreening,
                    rejected_after_full_interview = it.rejectedAfterFullInterview,
                    total_rejections = it.totalRejections,
                    in_progress = it.inProgress,
                    total_sent = it.totalSent,
                    interested = it.interested
                )
            )
        }
    }

    override suspend fun load(value: Airtable) {
        TODO("Not yet implemented")
    }
}
