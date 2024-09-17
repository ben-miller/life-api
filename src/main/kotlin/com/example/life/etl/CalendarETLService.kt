package com.example.life.etl

import com.example.life.model.ActivityMetrics
import com.example.life.model.CalendarDataSample
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service
import utility.UtilityServiceOuterClass.CalendarMetrics

fun CalendarMetrics.getTotalHoursOrNull(key: String): Double? {
    return try {
        getTotalHoursOrThrow(key)
    } catch (e: Exception) {
        null
    }
}

fun CalendarMetrics.getTotalSessionsOrNull(key: String): Int? {
    return try {
        getTotalSessionsOrThrow(key)
    } catch (e: Exception) {
        null
    }
}

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
            fun getActivityMetrics(hoursKey: String, sessionsKey: String): ActivityMetrics? {
                return try {
                    val hours = metrics.getTotalHoursOrNull(hoursKey)
                    val sessions = metrics.getTotalSessionsOrNull(sessionsKey)
                    if (hours != null && sessions != null) {
                        ActivityMetrics(hours, sessions)
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }

            CalendarDataSample(
                dev = getActivityMetrics("dev_total_hours", "dev_total_sessions"),
                chores = getActivityMetrics("chores_total_hours", "chores_total_sessions"),
                meditation = getActivityMetrics("meditation_total_hours", "meditation_total_sessions"),
                running = getActivityMetrics("running_total_hours", "running_total_sessions"),
                weight_training = getActivityMetrics("weight_training_total_hours", "weight_training_total_sessions"),
                job_search = getActivityMetrics("job_search_total_hours", "job_search_total_sessions"),
                leet_code = getActivityMetrics("leet_code_total_hours", "leet_code_total_sessions")
            )
        }
    }

    override suspend fun load(value: CalendarDataSample) {
    }
}
