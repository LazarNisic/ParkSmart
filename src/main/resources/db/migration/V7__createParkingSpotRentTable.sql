CREATE TABLE IF NOT EXISTS "parking_spot_rent" (
    id INTEGER PRIMARY KEY,
    city_id INTEGER NOT NULL,
    address VARCHAR(512) NOT NULL,
    is_available BOOLEAN NOT NULL,
    price_per_hour DOUBLE PRECISION NOT NULL,
    price_per_day DOUBLE PRECISION NULL,
    price_per_month DOUBLE PRECISION NULL,
    min_booking_duration INTEGER NOT NULL,
    owner_id INTEGER NOT NULL,
    access_id INTEGER,
    features_id INTEGER,
    "timestamp" TIMESTAMP NOT NULL,
    CONSTRAINT OWNER_FK FOREIGN KEY (owner_id) REFERENCES "user" (ID) ON DELETE CASCADE,
    CONSTRAINT CITY_FK FOREIGN KEY (city_id) REFERENCES "city" (ID) ON DELETE CASCADE,
    CONSTRAINT ACCESS_FK FOREIGN KEY (access_id) REFERENCES "parking_access" (ID) ON DELETE CASCADE,
    CONSTRAINT FEATURES_FK FOREIGN KEY (features_id) REFERENCES "features" (ID) ON DELETE CASCADE
);