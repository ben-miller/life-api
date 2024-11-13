package com.example.life

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.graphql.test.tester.GraphQlTester
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.Test

@SpringBootTest
@AutoConfigureGraphQlTester
@ExtendWith(SpringExtension::class)
class QueryTest {

    @Autowired
    lateinit var graphQlTester: GraphQlTester

    @Test
    fun `Test hello query`() {
        val query = """
            query {
              sources {
                obsidian {
                  inbox_total_items
                }
                trello {
                  inbox_size
                }
                youtube {
                  liked_videos
                }
                tidal {
                  uncategorized_tracks
                }
              }
            }
        """.trimIndent()

        graphQlTester.document(query)
            .execute()
            .also {
                println("result: " + it.path(""))
            }
            .path("sources.obsidian.inbox_total_items").entity(Int::class.java).isEqualTo(1154)
            .path("sources.trello.inbox_size").entity(Int::class.java).isEqualTo(2)
            .path("sources.youtube.liked_videos").entity(Int::class.java).isEqualTo(281)
    }
}
