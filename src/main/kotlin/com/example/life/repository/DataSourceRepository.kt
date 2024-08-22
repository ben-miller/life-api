package com.example.life.repository

import com.example.life.entity.DataSourceEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DataSourceRepository : CoroutineCrudRepository<DataSourceEntity, Int> {
    suspend fun findFirstBySourceName(sourceName: String): DataSourceEntity?
}
