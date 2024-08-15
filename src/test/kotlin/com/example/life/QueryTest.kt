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
                airtable {
                  job_search {
                    ignored_applications
                    rejected_applications
                    rejected_after_phone_screening
                    rejected_after_technical_screening
                    rejected_after_full_interview
                    total_rejections
                    in_progress
                    total_sent
                    interested
                  }
                }
                obsidian {
                  inboxes
                  inbox_items
                }
                trello {
                  inbox_size
                }
                youtube {
                  liked_videos
                }
                calendar {
                  dev_total_hours
                  chores_total_hours
                  meditation_total_hours
                  fitness_total_hours
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
            .path("sources.airtable.job_search.ignored_applications").entity(Int::class.java).isEqualTo(39)
            .path("sources.airtable.job_search.rejected_applications").entity(Int::class.java).isEqualTo(26)
            .path("sources.airtable.job_search.rejected_after_phone_screening").entity(Int::class.java).isEqualTo(2)
            .path("sources.airtable.job_search.rejected_after_technical_screening").entity(Int::class.java).isEqualTo(2)
            .path("sources.airtable.job_search.rejected_after_full_interview").entity(Int::class.java).isEqualTo(2)
            .path("sources.airtable.job_search.total_rejections").entity(Int::class.java).isEqualTo(71)
            .path("sources.airtable.job_search.in_progress").entity(Int::class.java).isEqualTo(17)
            .path("sources.airtable.job_search.total_sent").entity(Int::class.java).isEqualTo(88)
            .path("sources.airtable.job_search.interested").entity(Int::class.java).isEqualTo(47)
            .path("sources.obsidian.inboxes").entity(Int::class.java).isEqualTo(68)
            .path("sources.obsidian.inbox_items").entity(Int::class.java).isEqualTo(1154)
            .path("sources.trello.inbox_size").entity(Int::class.java).isEqualTo(2)
            .path("sources.youtube.liked_videos").entity(Int::class.java).isEqualTo(281)
            .path("sources.calendar.dev_total_hours").entity(Double::class.java).isEqualTo(18.0)
            .path("sources.calendar.chores_total_hours").entity(Double::class.java).isEqualTo(1.0)
            .path("sources.calendar.meditation_total_hours").entity(Double::class.java).isEqualTo(6.75)
            .path("sources.calendar.fitness_total_hours").entity(Double::class.java).isEqualTo(7.5)

    }
}
