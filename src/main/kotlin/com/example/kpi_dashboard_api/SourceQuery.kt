package com.example.kpi_dashboard_api
import com.expediagroup.graphql.server.operations.Query
import org.springframework.context.annotation.Profile
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
@Profile("test", "dev")
class SourceQuery : Query {

    @QueryMapping
    fun sources(): Source {
        return data
    }

    @SchemaMapping(typeName = "Source", field = "airtable")
    fun airtable(source: Source): Airtable {
        return source.airtable
    }

    @SchemaMapping(typeName = "Source", field = "desktop")
    fun desktop(source: Source): Desktop {
        return source.desktop
    }

    @SchemaMapping(typeName = "Source", field = "trello")
    fun trello(source: Source): Trello {
        return source.trello
    }

    @SchemaMapping(typeName = "Source", field = "google")
    fun google(source: Source): Google {
        return source.google
    }

    @SchemaMapping(typeName = "Source", field = "tidal")
    fun tidal(source: Source): Tidal {
        return source.tidal
    }
}
