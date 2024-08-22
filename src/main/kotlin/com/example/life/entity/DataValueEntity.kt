package com.example.life.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "DataValues")
data class DataValueEntity(
    @Id val id: Long? = null,
    val sourceId: Int,
    val metricName: String,
    val value: Double,
    val recordedAt: LocalDateTime
)
