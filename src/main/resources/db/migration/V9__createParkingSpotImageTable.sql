CREATE TABLE "parking_spot_image" (
    id INTEGER PRIMARY KEY,
    parking_spot_id INTEGER NOT NULL,
    image_url VARCHAR(512) NOT NULL,
    "timestamp" TIMESTAMP NOT NULL,
    CONSTRAINT PARKING_SPOT_FK FOREIGN KEY (parking_spot_id) REFERENCES "parking_spot" (ID) ON DELETE CASCADE
);