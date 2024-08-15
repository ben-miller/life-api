package com.example.life
import com.expediagroup.graphql.server.operations.Query
import org.springframework.context.annotation.Profile
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
@Profile("test")
class TestSourceQuery : Query {

    @QueryMapping
    fun sources(): Source {
        return data
    }

    @SchemaMapping(typeName = "Source", field = "airtable")
    fun airtable(source: Source): Airtable {
        return source.airtable
    }

    @SchemaMapping(typeName = "Source", field = "obsidian")
    fun obsidian(source: Source): Obsidian {
        return source.obsidian
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
