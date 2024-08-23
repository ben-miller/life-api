package com.example.life.repository

import com.example.life.entity.ObsidianDataSampleEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ObsidianDataSampleRepository : ReactiveCrudRepository<ObsidianDataSampleEntity, Int> {
    @Query("SELECT * FROM obsidian_data_samples ORDER BY recorded_at DESC LIMIT 1")
    suspend fun findLastSample(): ObsidianDataSampleEntity?
}
