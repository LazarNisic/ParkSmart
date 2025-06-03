CREATE TABLE IF NOT EXISTS "parking_spot_image" (
    id INTEGER PRIMARY KEY,
    parking_spot_rent_id INTEGER NOT NULL,
    image_url VARCHAR(512) NOT NULL,
    "timestamp" TIMESTAMP NOT NULL,
    CONSTRAINT PARKING_SPOT_RENT_FK FOREIGN KEY (parking_spot_rent_id) REFERENCES "parking_spot_rent" (ID) ON DELETE CASCADE
);