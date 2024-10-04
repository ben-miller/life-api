package com.example.life.etl

import com.example.life.entity.ObsidianDataSampleEntity
import com.example.life.model.ObsidianDataSample
import com.example.life.repository.ObsidianDataSampleRepository
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service

@Service
class ObsidianETLService(
    private val utilityService: UtilityService,
    private val obsidianDataSampleRepository: ObsidianDataSampleRepository,
    redisService: RedisService
) : CacheableETLService<ObsidianDataSample>(
    redisService,
    "obsidian",
    ObsidianDataSample::class.java
) {
    override suspend fun extract(): ObsidianDataSample {
        return utilityService.getObsidianMetrics().let {
            ObsidianDataSample(
                inbox_total_items = it.inboxTotalItems,
                life_inbox_dir = it.lifeInboxDir,
                journal_inbox_dir = it.journalInboxDir,
                administrivia_inbox_dir = it.administriviaInboxDir,
                desktop_inbox_dir = it.desktopInboxDir,
                library_inbox_dir = it.libraryInboxDir,
            )
        }
    }

    override suspend fun load(lastSample: ObsidianDataSample) {
        val lastEntry = obsidianDataSampleRepository.findLastSample()

        if (lastEntry == null ||
            lastEntry.inboxItems != lastSample.inbox_total_items) {
            obsidianDataSampleRepository.save(ObsidianDataSampleEntity(
                inboxItems = lastSample.inbox_total_items
            )).awaitSingle()
        }
    }
}
