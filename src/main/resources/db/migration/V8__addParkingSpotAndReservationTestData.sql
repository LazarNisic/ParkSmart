INSERT INTO parking_spot (id, city, address, is_available, price_per_hour, listing_type, owner_id)
VALUES (1, 'Beograd', 'Milovana Milovanovica', true, 2.5, 'RENT', 1);

INSERT INTO reservation (id, parking_spot_id, user_id, start_time, end_time, price_per_hour, status)
VALUES (1, 1, 1, '2025-03-17T09:00:00', '2025-03-17T17:00:00', 5, 'COMPLETED');
