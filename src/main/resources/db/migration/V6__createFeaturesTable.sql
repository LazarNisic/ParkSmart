CREATE TABLE IF NOT EXISTS "features" (
    id INTEGER PRIMARY KEY,
    supports_SUV BOOLEAN NULL,
    has_video_surveillance BOOLEAN NULL,
    accessible_for_disabled BOOLEAN NULL,
    night_lighting BOOLEAN NULL,
    has_EV_charging_station BOOLEAN NULL,
    "timestamp" TIMESTAMP NOT NULL
);