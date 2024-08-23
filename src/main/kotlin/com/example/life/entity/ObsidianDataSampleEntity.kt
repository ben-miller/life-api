package com.example.life.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("obsidian_data_samples")
class ObsidianDataSampleEntity(
    @Id val id: Long? = null,
    val recordedAt: LocalDateTime? = null,
    val inboxes: Int = 0,
    val inboxItems: Int = 0
)
