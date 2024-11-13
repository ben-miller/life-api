package com.example.life.service

import utility.UtilityServiceOuterClass.*

interface UtilityService {
    suspend fun getObsidianMetrics(): ObsidianMetrics
    suspend fun getFirefoxMetrics(): FirefoxMetrics
    suspend fun getOrgModeMetrics(): OrgModeMetrics
    suspend fun getYoutubeMetrics(): YoutubeMetrics
    suspend fun getTidalMetrics(): TidalMetrics
    suspend fun getCalendarMetrics(): CalendarMetrics
    suspend fun getTrelloMetrics(): TrelloMetrics
}

