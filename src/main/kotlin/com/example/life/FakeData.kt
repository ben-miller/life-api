package com.example.life

val data = Source(
    airtable = Airtable(
        job_search = JobSearch(
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
    obsidian = Obsidian(inboxes = 68, inbox_items = 1154),
    trello = Trello(inbox_size = 2),
    youtube = Youtube(liked_videos = 281),
    calendar = Calendar(
        dev_total_hours = 18.0,
        chores_total_hours = 1.0,
        meditation_total_hours = 6.75,
        fitness_total_hours = 7.5
    ),
    tidal = Tidal(uncategorized_tracks = 40),
    firefox = Firefox(bookmarks = 44),
    orgMode = OrgMode(inboxes = 43, inbox_items = 55)
)
