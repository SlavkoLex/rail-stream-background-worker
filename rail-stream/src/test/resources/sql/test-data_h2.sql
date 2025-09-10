CREATE TABLE devices (
    device_id SERIAL PRIMARY KEY, 
    device_name VARCHAR(100), 
    device_latitude DECIMAL(8, 6),
    device_longitude DECIMAL(9, 6)
);


CREATE TABLE row_device_data (
    row_device_data_id SERIAL PRIMARY KEY, 
    device_id INT, FOREIGN KEY (device_id) REFERENCES devices(device_id),
    train_data_timestamp TIMESTAMP,
    wheel_count_rail_input INTEGER,
    wheel_speed_rail_input SMALLINT,
    wheel_count_rail__output INTEGER,
    wheel_speed_rail_output SMALLINT,
    common_count_trains_passage_railway INTEGER,
    common_count_wheels_passage_railway INTEGER
);