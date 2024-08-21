package com.example.life.datasync

import com.example.life.datasource.CacheableETLService
import kotlinx.coroutines.delay
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.random.Random

private const val MAX_DELAY_5_MINS = 300_000L
private const val CRON_EVERY_HOUR = "0 0 * * * *"

@Service
class DataSyncServiceImpl(
    private val dataSources: List<CacheableETLService<*>>
) : DataSyncService {

    /**
     * Once every hour call all the APIs, with random delay for each.
     */
    @Scheduled(cron = CRON_EVERY_HOUR)
    override suspend fun syncEverythingWithRandomDelay() {
        dataSources.forEach { dataSource ->
            withRandomWait(MAX_DELAY_5_MINS) {
                println("Fetching w/ cache")
                dataSource.fetchWithCache(forceRefresh = true)
            }
        }
    }

    override suspend fun syncEverything() {
        dataSources.forEach { dataSource ->
            dataSource.fetchWithCache(forceRefresh = true)
        }
    }

    private suspend fun withRandomWait(until: Long, fetch: suspend () -> Unit) {
        delay(Random.nextLong(until))
        fetch()
    }
}
