package com.example.life.model

data class ActivityMetrics(
    val total_hours: Double,
    val total_sessions: Int
)

data class CalendarDataSample(
    val dev: ActivityMetrics? = null,
    val chores: ActivityMetrics? = null,
    val meditation: ActivityMetrics? = null,
    val running: ActivityMetrics? = null,
    val weight_training: ActivityMetrics? = null,
    val job_search: ActivityMetrics? = null
)
