CREATE TABLE IF NOT EXISTS bed_sensors
(
    id INT PRIMARY KEY,
    name VARCHAR(255),
    is_active BOOL DEFAULT FALSE
);