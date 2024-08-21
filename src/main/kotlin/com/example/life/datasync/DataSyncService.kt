package com.example.life.datasync

interface DataSyncService {
    suspend fun syncEverythingWithRandomDelay()
    suspend fun syncEverything()
}
