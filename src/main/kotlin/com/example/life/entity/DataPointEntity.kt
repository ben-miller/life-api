package com.example.life.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "DataPoints")
data class DataPointEntity(
    @Id val pointId: Int? = null,
    val sourceId: Int,
    val metricName: String,
    val lastValue: Double? = null,
    val lastUpdated: LocalDateTime? = null
)
