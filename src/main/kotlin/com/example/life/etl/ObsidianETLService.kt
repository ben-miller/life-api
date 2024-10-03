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
                inbox_items = it.inboxTotalItems,
                inbox_dir_size = it.inboxDirSize,
                journal_inbox_dir_size = it.journalInboxDirSize,
                administrivia_inbox_dir_size = it.administriviaInboxDirSize,
                desktop_dir_size = it.desktopDirSize
            )
        }
    }

    override suspend fun load(lastSample: ObsidianDataSample) {
        val lastEntry = obsidianDataSampleRepository.findLastSample()

        if (lastEntry == null ||
            lastEntry.inboxItems != lastSample.inbox_items) {
            obsidianDataSampleRepository.save(ObsidianDataSampleEntity(
                inboxItems = lastSample.inbox_items
            )).awaitSingle()
        }
    }
}
