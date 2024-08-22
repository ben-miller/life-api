package com.example.life.etl

import com.example.life.model.Calendar
import com.example.life.repository.DataSourceRepository
import com.example.life.service.RedisService
import com.example.life.service.UtilityService
import org.springframework.stereotype.Service

@Service
class CalendarETLService(
    private val utilityService: UtilityService,
    dataSourceRepository: DataSourceRepository,
    redisService: RedisService
) : CacheableETLService<Calendar>(
    redisService,
    dataSourceRepository,
    "calendar",
    Calendar::class.java
) {
    override suspend fun extract(): Calendar {
        return utilityService.getCalendarMetrics().let {
            Calendar(
                dev_total_hours = it.devTotalHours,
                chores_total_hours = it.choresTotalHours,
                meditation_total_hours = it.meditationTotalHours,
                fitness_total_hours = it.fitnessTotalHours
            )
        }
    }

    override suspend fun load(value: Calendar) {
        TODO("Not yet implemented")
    }
}
