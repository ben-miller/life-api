package com.example.life

import kotlinx.coroutines.delay
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class DataSyncService {

    /**
     * Once every hour call all the APIs, with random delay for each.
     */
    @Scheduled(cron = "0 0 * * * *")
    suspend fun syncEverythingWithRandomDelay() {
        // Delay randomly within 0-5 minutes.
        delay(Random.nextLong(300000))
        syncEverything()
    }

    suspend fun syncEverything() {

    }
}
