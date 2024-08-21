package com.example.life.datasource

import com.example.life.Calendar
import com.example.life.RedisService
import com.example.life.UtilityService
import org.springframework.stereotype.Service

@Service
class CalendarDataSource(
    private val utilityService: UtilityService,
    redisService: RedisService
) : CacheableDataSource<Calendar>(
    redisService,
    "source.calendar",
    Calendar::class.java
) {
    override suspend fun fetch(): Calendar {
        return utilityService.getCalendarMetrics().let {
            Calendar(
                dev_total_hours = it.devTotalHours,
                chores_total_hours = it.choresTotalHours,
                meditation_total_hours = it.meditationTotalHours,
                fitness_total_hours = it.fitnessTotalHours
            )
        }
    }

    override suspend fun save(value: Calendar) {
        TODO("Not yet implemented")
    }
}
