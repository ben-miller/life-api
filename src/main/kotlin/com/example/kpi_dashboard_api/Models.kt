package com.example.kpi_dashboard_api

data class JobSearch(
    val ignored_applications: Int,
    val rejected_applications: Int,
    val rejected_after_phone_screening: Int,
    val rejected_after_technical_screening: Int,
    val rejected_after_full_interview: Int,
    val total_rejections: Int,
    val in_progress: Int,
    val total_sent: Int,
    val interested: Int
)

data class Firefox(val bookmarks: Int)
data class Obsidian(val inboxes: Int, val inbox_items: Int)
data class OrgMode(val inboxes: Int, val inbox_items: Int)
data class Youtube(val liked_videos: Int)
data class Tidal(
    val dev_total_hours: Double,
    val chores_total_hours: Double,
    val meditation_total_hours: Double,
    val fitness_total_hours: Double
)

data class Airtable(val job_search: JobSearch)
data class Desktop(val firefox: Firefox, val obsidian: Obsidian, val org_mode: OrgMode)
data class Trello(val inbox_size: Int)
data class Google(val youtube: Youtube)

data class Source(
    val airtable: Airtable,
    val desktop: Desktop,
    val trello: Trello,
    val google: Google,
    val tidal: Tidal
)
