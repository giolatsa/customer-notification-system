
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS addresses (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(id),
    address_type VARCHAR(50) NOT NULL,
    value VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS preferences (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(id),
    notification_type VARCHAR(50) NOT NULL,
    opted_in BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS notifications (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(id),
    notification_type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP NOT NULL
);