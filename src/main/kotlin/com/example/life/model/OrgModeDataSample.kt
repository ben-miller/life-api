package com.example.life.model

data class OrgModeTask(
    val label: String,
    val state: String
)

data class OrgModeDataSample(
    val inbox_items: Int,
    val total_bugs: Int,
    val project_tasks: List<OrgModeTask>
)
