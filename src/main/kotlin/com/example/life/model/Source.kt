package com.example.life.model

data class Source(
    var obsidian: ObsidianDataSample? = null,
    var trello: TrelloDataSample? = null,
    var tidal: TidalDataSample? = null,
    var firefox: FirefoxDataSample? = null,
    var orgMode: OrgModeDataSample? = null,
    var youtube: YoutubeDataSample? = null,
    var calendar: CalendarDataSample? = null
)