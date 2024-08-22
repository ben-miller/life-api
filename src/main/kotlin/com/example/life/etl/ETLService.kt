package com.example.life.etl

import com.example.life.entity.DataSourceEntity
import com.example.life.repository.DataSourceRepository

abstract class ETLService<T : Any>(
    private val dataSourceRepository: DataSourceRepository,
    protected val sourceName: String
) {
    abstract suspend fun extract(): T
    abstract suspend fun load(value: T)
    suspend fun fetchAndSave() {
        val newValue: T = extract()
        load(newValue)
    }
    protected lateinit var dataSource: DataSourceEntity
    suspend fun dbInit() {
        // Find DataSource entity from db, or create it.
        dataSource = dataSourceRepository.findFirstBySourceName(sourceName)
            ?: dataSourceRepository.save(DataSourceEntity(sourceName = sourceName))
    }
}
