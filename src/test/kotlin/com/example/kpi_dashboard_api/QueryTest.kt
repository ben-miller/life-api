package com.example.kpi_dashboard_api

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
                hello
            }
        """.trimIndent()

        graphQlTester.document(query)
            .execute()
            .path("hello")
            .entity(String::class.java)
            .isEqualTo("Hello, GraphQL!")
    }
}
