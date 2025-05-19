CREATE TABLE IF NOT EXISTS "parking_access" (
    id INTEGER PRIMARY KEY,
    access_type VARCHAR(50) NOT NULL,
    number_of_accesses VARCHAR(50) NOT NULL,
    access_time_start TIMESTAMP NOT NULL,
    access_time_end TIMESTAMP NOT NULL,
    "timestamp" TIMESTAMP NOT NULL
);