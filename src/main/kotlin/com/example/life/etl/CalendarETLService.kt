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
        return utilityService.getCalendarMetrics().let {
            CalendarDataSample(
                dev_total_hours = it.devTotalHours,
                chores_total_hours = it.choresTotalHours,
                meditation_total_hours = it.meditationTotalHours,
                running_total_hours = it.weightTrainingTotalHours,
                weight_training_total_hours = it.weightTrainingTotalHours,
                dev_total_sessions = it.devTotalSessions,
                chores_total_sessions = it.choresTotalSessions,
                meditation_total_sessions = it.meditationTotalSessions,
                running_total_sessions = it.runningTotalSessions,
                weight_training_total_sessions = it.weightTrainingTotalSessions
            )
        }
    }

    override suspend fun load(value: CalendarDataSample) {
    }
}
