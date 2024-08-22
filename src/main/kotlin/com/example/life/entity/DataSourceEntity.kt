package com.example.life.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "DataSources")
data class DataSourceEntity(
    @Id val sourceId: Int? = null,
    val sourceName: String,
    val lastUpdated: LocalDateTime? = null
)
