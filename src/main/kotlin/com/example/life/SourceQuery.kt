package com.example.life

import com.example.life.GraphQLConstants.Fields.AIRTABLE
import com.example.life.GraphQLConstants.Fields.CALENDAR
import com.example.life.GraphQLConstants.Fields.FIREFOX
import com.example.life.GraphQLConstants.Fields.OBSIDIAN
import com.example.life.GraphQLConstants.Fields.ORG_MODE
import com.example.life.GraphQLConstants.Fields.TIDAL
import com.example.life.GraphQLConstants.Fields.TRELLO
import com.example.life.GraphQLConstants.Fields.YOUTUBE
import com.example.life.GraphQLConstants.TypeNames.SOURCE
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

    @SchemaMapping(typeName = SOURCE, field = AIRTABLE)
    suspend fun airtable(@Argument forceRefresh: Boolean = false): Airtable =
        airtableDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = OBSIDIAN)
    suspend fun obsidian(@Argument forceRefresh: Boolean = false): Obsidian =
        obsidianDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = TRELLO)
    suspend fun trello(@Argument forceRefresh: Boolean = false): Trello =
        trelloDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = YOUTUBE)
    suspend fun youtube(@Argument forceRefresh: Boolean = false): Youtube =
        youtubeDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = TIDAL)
    suspend fun tidal(@Argument forceRefresh: Boolean = false): Tidal =
        tidalDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = FIREFOX)
    suspend fun firefox(@Argument forceRefresh: Boolean = false): Firefox =
        firefoxDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = ORG_MODE)
    suspend fun orgMode(@Argument forceRefresh: Boolean = false): OrgMode =
        orgModeDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = CALENDAR)
    suspend fun calendar(@Argument forceRefresh: Boolean = false): Calendar =
        calendarDataSource.fetchWithCache(forceRefresh)
}
