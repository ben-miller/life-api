package com.example.life.datasource

import com.example.life.Airtable
import com.example.life.JobSearch
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service


@Service
class AirtableDataSource(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableDataSource<Airtable>(
    redisService,
    "source.airtable",
    Airtable::class.java
) {
    override suspend fun fetch(): Airtable {
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

    override suspend fun save(value: Airtable) {
        TODO("Not yet implemented")
    }
}
