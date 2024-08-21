package com.example.life.model

data class Source(
    var airtable: Airtable? = null,
    var obsidian: Obsidian? = null,
    var trello: Trello? = null,
    var tidal: Tidal? = null,
    var firefox: Firefox? = null,
    var orgMode: OrgMode? = null,
    var youtube: Youtube? = null,
    var calendar: Calendar? = null
)