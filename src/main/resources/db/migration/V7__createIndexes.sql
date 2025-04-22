CREATE INDEX idx_user_user_name ON "user" (user_name);
CREATE INDEX idx_parking_spot_city ON "parking_spot" (city, is_available);
CREATE INDEX idx_reservation_time ON "reservation" (start_time, end_time);