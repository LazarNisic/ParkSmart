CREATE TABLE "parking_spot" (
    id INTEGER PRIMARY KEY,
    city VARCHAR(50) NOT NULL,
    address VARCHAR(512) NOT NULL,
    is_available BOOLEAN NOT NULL,
    price_per_hour DOUBLE PRECISION NOT NULL,
    listing_type VARCHAR(50) NOT NULL,
    owner_id INTEGER NOT NULL,
    CONSTRAINT OWNER_FK FOREIGN KEY (owner_id) REFERENCES "user" (ID) ON DELETE CASCADE
);