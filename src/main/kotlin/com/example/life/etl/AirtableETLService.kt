package com.example.life.etl

import com.example.life.model.AirtableDataSample
import com.example.life.model.JobSearchDataSample
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service


@Service
class AirtableETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<AirtableDataSample>(
    redisService,
    "airtable",
    AirtableDataSample::class.java
) {
    override suspend fun extract(): AirtableDataSample {
        return utilityService.getAirtableJobSearchMetrics().let {
            AirtableDataSample(
                JobSearchDataSample(
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

    override suspend fun load(value: AirtableDataSample) {
    }
}
