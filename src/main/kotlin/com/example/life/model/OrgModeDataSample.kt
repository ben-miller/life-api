package com.example.life.model

data class OrgModeTask(
    val task: String,
    val state: String
)

data class OrgModeDataSample(
    val inboxes: Int,
    val inbox_items: Int,
    val project_tasks: List<OrgModeTask>
)
