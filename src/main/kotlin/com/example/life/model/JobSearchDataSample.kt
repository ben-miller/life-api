package com.example.life.model

data class JobSearchDataSample(
    val ignored_applications: Int,
    val rejected_applications: Int,
    val rejected_after_phone_screening: Int,
    val rejected_after_technical_screening: Int,
    val rejected_after_full_interview: Int,
    val total_rejections: Int,
    val in_progress: Int,
    val total_sent: Int,
    val interested: Int
)
