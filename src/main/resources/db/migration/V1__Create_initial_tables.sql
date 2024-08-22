CREATE TABLE DataSources (
                             id SERIAL PRIMARY KEY,
                             source_name TEXT NOT NULL,
                             last_updated TIMESTAMP
);

CREATE TABLE DataPoints (
                            id SERIAL PRIMARY KEY,
                            source_id INTEGER REFERENCES DataSources(id),
                            metric_name TEXT NOT NULL,
                            last_value REAL,
                            last_updated TIMESTAMP
);

CREATE TABLE DataValues (
                            value_id SERIAL PRIMARY KEY,
                            point_id INTEGER REFERENCES DataPoints(id),
                            "value" REAL,
                            recorded_at TIMESTAMP
);
