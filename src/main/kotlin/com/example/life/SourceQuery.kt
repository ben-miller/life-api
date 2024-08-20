package com.example.life

import com.example.life.datasource.*
import com.expediagroup.graphql.server.operations.Query
import org.springframework.context.annotation.Profile
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
@Profile("!test")
class SourceQuery(
    private val airtableDataSource: AirtableDataSource,
    private val obsidianDataSource: ObsidianDataSource,
    private val trelloDataSource: TrelloDataSource,
    private val youtubeDataSource: YoutubeDataSource,
    private val tidalDataSource: TidalDataSource,
    private val firefoxDataSource: FirefoxDataSource,
    private val orgModeDataSource: OrgModeDataSource,
    private val calendarDataSource: CalendarDataSource
) : Query {

    @QueryMapping
    suspend fun sources(): Source {
        return Source()
    }

    @SchemaMapping(typeName = "Source", field = "airtable")
    suspend fun airtable(@Argument forceRefresh: Boolean = false): Airtable =
        airtableDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = "Source", field = "obsidian")
    suspend fun obsidian(@Argument forceRefresh: Boolean = false): Obsidian =
        obsidianDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = "Source", field = "trello")
    suspend fun trello(@Argument forceRefresh: Boolean = false): Trello =
        trelloDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = "Source", field = "youtube")
    suspend fun youtube(@Argument forceRefresh: Boolean = false): Youtube =
        youtubeDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = "Source", field = "tidal")
    suspend fun tidal(@Argument forceRefresh: Boolean = false): Tidal =
        tidalDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = "Source", field = "firefox")
    suspend fun firefox(@Argument forceRefresh: Boolean = false): Firefox =
        firefoxDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = "Source", field = "org_mode")
    suspend fun orgMode(@Argument forceRefresh: Boolean = false): OrgMode =
        orgModeDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = "Source", field = "calendar")
    suspend fun calendar(@Argument forceRefresh: Boolean = false): Calendar =
        calendarDataSource.fetchWithCache(forceRefresh)
}
