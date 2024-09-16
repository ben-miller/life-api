package com.example.life.etl

import com.example.life.model.CalendarDataSample
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class CalendarETLService(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableETLService<CalendarDataSample>(
    redisService,
    "calendar",
    CalendarDataSample::class.java
) {
    override suspend fun extract(): CalendarDataSample {
        return utilityService.getCalendarMetrics().let { metrics ->
            CalendarDataSample(
                dev_total_hours = metrics.getTotalHoursOrThrow("dev_total_hours"),
                chores_total_hours = metrics.getTotalHoursOrThrow("chores_total_hours"),
                meditation_total_hours = metrics.getTotalHoursOrThrow("meditation_total_hours"),
                running_total_hours = metrics.getTotalHoursOrThrow("running_total_hours"),
                weight_training_total_hours = metrics.getTotalHoursOrThrow("weight_training_total_hours"),
                dev_total_sessions = metrics.getTotalSessionsOrThrow("dev_total_sessions"),
                chores_total_sessions = metrics.getTotalSessionsOrThrow("chores_total_sessions"),
                meditation_total_sessions = metrics.getTotalSessionsOrThrow("meditation_total_sessions"),
                running_total_sessions = metrics.getTotalSessionsOrThrow("running_total_sessions"),
                weight_training_total_sessions = metrics.getTotalSessionsOrThrow("weight_training_total_sessions")
            )
        }
    }

    override suspend fun load(value: CalendarDataSample) {
    }
}
