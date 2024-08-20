package com.example.life

import org.springframework.stereotype.Service
import utility.UtilityServiceGrpcKt
import utility.UtilityServiceOuterClass.*

interface UtilityService {
    suspend fun getObsidianMetrics(): ObsidianMetrics
    suspend fun getAirtableJobSearchMetrics(): AirtableJobSearchMetrics
    suspend fun getFirefoxMetrics(): FirefoxMetrics
    suspend fun getOrgModeMetrics(): OrgModeMetrics
    suspend fun getYoutubeMetrics(): YoutubeMetrics
    suspend fun getTidalMetrics(): TidalMetrics
    suspend fun getCalendarMetrics(): CalendarMetrics
    suspend fun getTrelloMetrics(): TrelloMetrics
}

private val EmptyReq = EmptyRequest.newBuilder().build()

@Service
class UtilityServiceImpl(
    private val stub: UtilityServiceGrpcKt.UtilityServiceCoroutineStub
) : UtilityService {
    override suspend fun getObsidianMetrics(): ObsidianMetrics = stub.getObsidianMetrics(EmptyReq)
    override suspend fun getAirtableJobSearchMetrics(): AirtableJobSearchMetrics = stub.getAirtableJobSearchMetrics(EmptyReq)
    override suspend fun getFirefoxMetrics(): FirefoxMetrics = stub.getFirefoxMetrics(EmptyReq)
    override suspend fun getOrgModeMetrics(): OrgModeMetrics = stub.getOrgModeMetrics(EmptyReq)
    override suspend fun getYoutubeMetrics(): YoutubeMetrics = stub.getYoutubeMetrics(EmptyReq)
    override suspend fun getTidalMetrics(): TidalMetrics = stub.getTidalMetrics(EmptyReq)
    override suspend fun getCalendarMetrics(): CalendarMetrics = stub.getCalendarMetrics(EmptyReq)
    override suspend fun getTrelloMetrics(): TrelloMetrics = stub.getTrelloMetrics(EmptyReq)
}
