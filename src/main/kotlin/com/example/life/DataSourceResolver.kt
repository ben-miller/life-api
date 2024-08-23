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
import com.example.life.etl.*
import com.example.life.model.*
import com.expediagroup.graphql.server.operations.Query
import org.springframework.context.annotation.Profile
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
@Profile("!test")
class DataSourceResolver(
    private val airtableDataSource: AirtableETLService,
    private val obsidianDataSource: ObsidianETLService,
    private val trelloDataSource: TrelloETLService,
    private val youtubeDataSource: YoutubeETLService,
    private val tidalDataSource: TidalETLService,
    private val firefoxDataSource: FirefoxETLService,
    private val orgModeDataSource: OrgModeETLService,
    private val calendarDataSource: CalendarETLService
) : Query {

    @QueryMapping
    suspend fun sources(): Source {
        return Source()
    }

    @SchemaMapping(typeName = SOURCE, field = AIRTABLE)
    suspend fun airtable(@Argument forceRefresh: Boolean = false): AirtableDataSample =
        airtableDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = OBSIDIAN)
    suspend fun obsidian(@Argument forceRefresh: Boolean = false): ObsidianDataSample =
        obsidianDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = TRELLO)
    suspend fun trello(@Argument forceRefresh: Boolean = false): TrelloDataSample =
        trelloDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = YOUTUBE)
    suspend fun youtube(@Argument forceRefresh: Boolean = false): YoutubeDataSample =
        youtubeDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = TIDAL)
    suspend fun tidal(@Argument forceRefresh: Boolean = false): TidalDataSample =
        tidalDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = FIREFOX)
    suspend fun firefox(@Argument forceRefresh: Boolean = false): FirefoxDataSample =
        firefoxDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = ORG_MODE)
    suspend fun orgMode(@Argument forceRefresh: Boolean = false): OrgModeDataSample =
        orgModeDataSource.fetchWithCache(forceRefresh)

    @SchemaMapping(typeName = SOURCE, field = CALENDAR)
    suspend fun calendar(@Argument forceRefresh: Boolean = false): CalendarDataSample =
        calendarDataSource.fetchWithCache(forceRefresh)
}
