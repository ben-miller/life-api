package com.example.life

import com.expediagroup.graphql.server.operations.Query
import kotlinx.coroutines.withContext
import org.springframework.context.annotation.Profile
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
@Profile("!test")
class SourceQuery(
    private val utilityService: UtilityService,
    private val rs: RedisService
) : Query {

    @QueryMapping
    suspend fun sources(): Source {
        return Source()
    }

    @SchemaMapping(typeName = "Source", field = "airtable")
    suspend fun airtable(): Airtable {
        return rs.withCached("source.airtable", Airtable::class.java) {
            utilityService.getAirtableJobSearchMetrics().let {
                Airtable(
                    JobSearch(
                        ignored_applications = it.ignoredApplications,
                        rejected_applications = it.rejectedApplications,
                        rejected_after_phone_screening = it.rejectedAfterPhoneScreening,
                        rejected_after_technical_screening = it.rejectedAfterTechnicalScreening,
                        rejected_after_full_interview = it.rejectedAfterFullInterview,
                        total_rejections = it.totalRejections,
                        in_progress = it.inProgress,
                        total_sent = it.totalSent,
                        interested = it.interested
                    )
                )
            }
        }
    }

    @SchemaMapping(typeName = "Source", field = "obsidian")
    suspend fun obsidian(): Obsidian =
        utilityService.getObsidianMetrics().let {
            Obsidian(it.inboxesCount, it.inboxTotalItems)
        }

    @SchemaMapping(typeName = "Source", field = "trello")
    suspend fun trello(): Trello =
        utilityService.getTrelloMetrics().let {
            Trello(it.inboxSize)
        }

    @SchemaMapping(typeName = "Source", field = "youtube")
    suspend fun youtube(): Youtube =
        utilityService.getYoutubeMetrics().let {
            Youtube(it.likedVideosCount)
        }

    @SchemaMapping(typeName = "Source", field = "tidal")
    suspend fun tidal(): Tidal =
        utilityService.getTidalMetrics().let {
            Tidal(it.uncategorizedTracks)
        }

    @SchemaMapping(typeName = "Source", field = "firefox")
    suspend fun firefox(): Firefox =
        utilityService.getFirefoxMetrics().let {
            Firefox(it.bookmarksCount)
        }

    @SchemaMapping(typeName = "Source", field = "org_mode")
    suspend fun orgMode(): OrgMode =
        utilityService.getOrgModeMetrics().let {
            OrgMode(it.inboxesCount, it.inboxTotalItems)
        }

    @SchemaMapping(typeName = "Source", field = "calendar")
    suspend fun calendar(): Calendar =
        utilityService.getCalendarMetrics().let {
            Calendar(
                dev_total_hours = it.devTotalHours,
                chores_total_hours = it.choresTotalHours,
                meditation_total_hours = it.meditationTotalHours,
                fitness_total_hours = it.fitnessTotalHours
            )
        }
}
