package com.example.kpi_dashboard_api

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class Query {

    @QueryMapping
    fun hello(): String {
        return "Hello, GraphQL!"
    }
}
