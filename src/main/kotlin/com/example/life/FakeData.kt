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
    desktop = Desktop(
        firefox = Firefox(bookmarks = 67),
        obsidian = Obsidian(inboxes = 68, inbox_items = 1154),
        org_mode = OrgMode(inboxes = 9, inbox_items = 117)
    ),
    trello = Trello(inbox_size = 2),
    google = Google(
        youtube = Youtube(liked_videos = 281)
    ),
    tidal = Tidal(
        dev_total_hours = 18.0,
        chores_total_hours = 1.0,
        meditation_total_hours = 6.75,
        fitness_total_hours = 7.5
    )
)
