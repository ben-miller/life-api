-- Base table for common fields
CREATE TABLE base_sample (
                                          id SERIAL PRIMARY KEY,
                                          recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- airtable_job_search_data_samples table
CREATE TABLE airtable_job_search_data_samples (
                                                               ignored_applications INT DEFAULT 0,
                                                               rejected_applications INT DEFAULT 0,
                                                               rejected_after_phone_screening INT DEFAULT 0,
                                                               rejected_after_technical_screening INT DEFAULT 0,
                                                               rejected_after_full_interview INT DEFAULT 0,
                                                               total_rejections INT DEFAULT 0,
                                                               in_progress INT DEFAULT 0,
                                                               total_sent INT DEFAULT 0,
                                                               interested INT DEFAULT 0
) INHERITS (base_sample);
CREATE INDEX idx_airtable_job_search_data_samples_recorded_at
    ON airtable_job_search_data_samples (recorded_at DESC);

-- obsidian_data_samples table
CREATE TABLE obsidian_data_samples (
                                                    inboxes INT DEFAULT 0,
                                                    inbox_items INT DEFAULT 0
) INHERITS (base_sample);
CREATE INDEX idx_obsidian_data_samples_recorded_at
    ON obsidian_data_samples (recorded_at DESC);

-- youtube_data_samples table
CREATE TABLE youtube_data_samples (
                                                   liked_videos INT DEFAULT 0
) INHERITS (base_sample);
CREATE INDEX idx_youtube_data_samples_recorded_at
    ON youtube_data_samples (recorded_at DESC);

-- calendar_data_samples table
CREATE TABLE calendar_data_samples (
                                                    dev_total_hours DECIMAL(10, 2) DEFAULT 0.0,
                                                    chores_total_hours DECIMAL(10, 2) DEFAULT 0.0,
                                                    meditation_total_hours DECIMAL(10, 2) DEFAULT 0.0,
                                                    fitness_total_hours DECIMAL(10, 2) DEFAULT 0.0
) INHERITS (base_sample);
CREATE INDEX idx_calendar_data_samples_recorded_at
    ON calendar_data_samples (recorded_at DESC);

-- tidal_data_samples table
CREATE TABLE tidal_data_samples (
                                                 uncategorized_tracks INT DEFAULT 0
) INHERITS (base_sample);
CREATE INDEX idx_tidal_data_samples_recorded_at
    ON tidal_data_samples (recorded_at DESC);

-- org_mode_data_samples table
CREATE TABLE org_mode_data_samples (
                                                    inboxes INT DEFAULT 0,
                                                    inbox_items INT DEFAULT 0
) INHERITS (base_sample);
CREATE INDEX idx_org_mode_data_samples_recorded_at
    ON org_mode_data_samples (recorded_at DESC);

-- firefox_data_samples table
CREATE TABLE firefox_data_samples (
                                                   bookmarks INT DEFAULT 0
) INHERITS (base_sample);
CREATE INDEX idx_firefox_data_samples_recorded_at
    ON firefox_data_samples (recorded_at DESC);
