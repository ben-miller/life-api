package com.example.life

import io.grpc.ManagedChannel
import org.springframework.stereotype.Service
import utility.UtilityServiceGrpc
import utility.UtilityServiceOuterClass.*

interface UtilityService {
    fun getObsidianMetrics(): ObsidianMetrics
    fun getAirtableJobSearchMetrics(): AirtableJobSearchMetrics
    fun getFirefoxMetrics(): FirefoxMetrics
    fun getOrgModeMetrics(): OrgModeMetrics
    fun getYoutubeMetrics(): YoutubeMetrics
    fun getTidalMetrics(): TidalMetrics
    fun getCalendarMetrics(): CalendarMetrics
    fun getTrelloMetrics(): TrelloMetrics
}

private val EmptyReq = EmptyRequest.newBuilder().build()

@Service
class UtilityServiceImpl(
    private val channel: ManagedChannel,
    private val stub: UtilityServiceGrpc.UtilityServiceBlockingStub
) : UtilityService {
    override fun getObsidianMetrics(): ObsidianMetrics = stub.getObsidianMetrics(EmptyReq)
    override fun getAirtableJobSearchMetrics(): AirtableJobSearchMetrics = stub.getAirtableJobSearchMetrics(EmptyReq)
    override fun getFirefoxMetrics(): FirefoxMetrics = stub.getFirefoxMetrics(EmptyReq)
    override fun getOrgModeMetrics(): OrgModeMetrics = stub.getOrgModeMetrics(EmptyReq)
    override fun getYoutubeMetrics(): YoutubeMetrics = stub.getYoutubeMetrics(EmptyReq)
    override fun getTidalMetrics(): TidalMetrics = stub.getTidalMetrics(EmptyReq)
    override fun getCalendarMetrics(): CalendarMetrics = stub.getCalendarMetrics(EmptyReq)
    override fun getTrelloMetrics(): TrelloMetrics = stub.getTrelloMetrics(EmptyReq)
}
