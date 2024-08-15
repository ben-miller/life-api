package com.example.life

import com.expediagroup.graphql.server.operations.Query
import org.springframework.context.annotation.Profile
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
@Profile("!test")
class SourceQuery(
    private val utilityService: UtilityService
) : Query {

    @QueryMapping
    fun sources(): Source {
        return Source(
            airtable = airtable(),
            obsidian = obsidian(),
            trello = trello(),
            tidal = tidal(),
            firefox = firefox(),
            orgMode = orgMode(),
            youtube = youtube(),
            calendar = calendar()
        )
    }

    @SchemaMapping(typeName = "Source", field = "airtable")
    fun airtable(): Airtable =
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

    @SchemaMapping(typeName = "Source", field = "obsidian")
    fun obsidian(): Obsidian =
        utilityService.getObsidianMetrics().let {
            Obsidian(it.inboxesCount, it.inboxTotalItems)
        }

    @SchemaMapping(typeName = "Source", field = "trello")
    fun trello(): Trello =
        utilityService.getTrelloMetrics().let {
            Trello(it.inboxSize)
        }

    @SchemaMapping(typeName = "Source", field = "google")
    fun youtube(): Youtube =
        utilityService.getYoutubeMetrics().let {
            Youtube(it.likedVideosCount)
        }

    @SchemaMapping(typeName = "Source", field = "tidal")
    fun tidal(): Tidal =
        utilityService.getTidalMetrics().let {
            Tidal(it.uncategorizedTracks)
        }

    @SchemaMapping(typeName = "Source", field = "firefox")
    fun firefox(): Firefox =
        utilityService.getFirefoxMetrics().let {
            Firefox(it.bookmarksCount)
        }

    @SchemaMapping(typeName = "Source", field = "org_mode")
    fun orgMode(): OrgMode =
        utilityService.getOrgModeMetrics().let {
            OrgMode(it.inboxesCount, it.inboxTotalItems)
        }

    @SchemaMapping(typeName = "Source", field = "calendar")
    fun calendar(): Calendar =
        utilityService.getCalendarMetrics().let {
            Calendar(
                dev_total_hours = it.devTotalHours,
                chores_total_hours = it.choresTotalHours,
                meditation_total_hours = it.meditationTotalHours,
                fitness_total_hours = it.fitnessTotalHours
            )
        }
}
