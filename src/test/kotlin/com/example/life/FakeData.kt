package com.example.life

import com.example.life.model.*

val data = Source(
    airtable = AirtableDataSample(
        job_search = JobSearchDataSample(
            ignored_applications = 39,
            rejected_applications = 26,
            rejected_after_phone_screening = 2,
            rejected_after_technical_screening = 2,
            rejected_after_full_interview = 2,
            total_rejections = 71,
            in_progress = 17,
            total_sent = 88,
            interested = 47
        )
    ),
    obsidian = ObsidianDataSample(inboxes = 68, inbox_items = 1154),
    trello = TrelloDataSample(inbox_size = 2),
    youtube = YoutubeDataSample(liked_videos = 281),
    calendar = CalendarDataSample(
        dev_total_hours = 18.0,
        chores_total_hours = 1.0,
        meditation_total_hours = 6.75,
        running_total_hours = 7.5,
        weight_training_total_hours = 7.5,
        dev_total_sessions = 9,
        chores_total_sessions = 11,
        meditation_total_sessions = 13,
        running_total_sessions = 7,
        weight_training_total_sessions = 2,
    ),
    tidal = TidalDataSample(uncategorized_tracks = 40),
    firefox = FirefoxDataSample(bookmarks = 44),
    orgMode = OrgModeDataSample(inboxes = 43, inbox_items = 55)
)
