package com.example.life

import com.expediagroup.graphql.server.operations.Query
import org.springframework.context.annotation.Profile
import org.springframework.graphql.data.method.annotation.Argument
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
    suspend fun airtable(@Argument forceRefresh: Boolean = false): Airtable =
        rs.withCached("source.airtable", Airtable::class.java, forceRefresh) {
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

    @SchemaMapping(typeName = "Source", field = "obsidian")
    suspend fun obsidian(@Argument forceRefresh: Boolean = false): Obsidian =
        rs.withCached("source.obsidian", Obsidian::class.java, forceRefresh) {
            utilityService.getObsidianMetrics().let {
                Obsidian(it.inboxesCount, it.inboxTotalItems)
            }
        }

    @SchemaMapping(typeName = "Source", field = "trello")
    suspend fun trello(@Argument forceRefresh: Boolean = false): Trello =
        rs.withCached("source.trello", Trello::class.java, forceRefresh) {
            utilityService.getTrelloMetrics().let {
                Trello(it.inboxSize)
            }
        }

    @SchemaMapping(typeName = "Source", field = "youtube")
    suspend fun youtube(@Argument forceRefresh: Boolean = false): Youtube =
        rs.withCached("source.youtube", Youtube::class.java, forceRefresh) {
            utilityService.getYoutubeMetrics().let {
                Youtube(it.likedVideosCount)
            }
        }

    @SchemaMapping(typeName = "Source", field = "tidal")
    suspend fun tidal(@Argument forceRefresh: Boolean = false): Tidal =
        rs.withCached("source.tidal", Tidal::class.java, forceRefresh) {
            utilityService.getTidalMetrics().let {
                Tidal(it.uncategorizedTracks)
            }
        }

    @SchemaMapping(typeName = "Source", field = "firefox")
    suspend fun firefox(@Argument forceRefresh: Boolean = false): Firefox =
        rs.withCached("source.firefox", Firefox::class.java, forceRefresh) {
            utilityService.getFirefoxMetrics().let {
                Firefox(it.bookmarksCount)
            }
        }

    @SchemaMapping(typeName = "Source", field = "org_mode")
    suspend fun orgMode(@Argument forceRefresh: Boolean = false): OrgMode =
        rs.withCached("source.orgMode", OrgMode::class.java, forceRefresh) {
            utilityService.getOrgModeMetrics().let {
                OrgMode(it.inboxesCount, it.inboxTotalItems)
            }
        }

    @SchemaMapping(typeName = "Source", field = "calendar")
    suspend fun calendar(@Argument forceRefresh: Boolean = false): Calendar =
        rs.withCached("source.calendar", Calendar::class.java, forceRefresh) {
            utilityService.getCalendarMetrics().let {
                Calendar(
                    dev_total_hours = it.devTotalHours,
                    chores_total_hours = it.choresTotalHours,
                    meditation_total_hours = it.meditationTotalHours,
                    fitness_total_hours = it.fitnessTotalHours
                )
            }
        }
}
