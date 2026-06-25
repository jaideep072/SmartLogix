-- Run this script in pgAdmin to create the tables for the smartlogix database

CREATE TABLE IF NOT EXISTS warehouses (
    warehouse_id VARCHAR(50) PRIMARY KEY,
    warehouse_name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS shipments (
    shipment_id INT PRIMARY KEY,
    source_warehouse VARCHAR(50) NOT NULL,
    destination_warehouse VARCHAR(50) NOT NULL,
    delivery_date DATE NOT NULL,
    priority INT NOT NULL,
    cost NUMERIC(10, 2) NOT NULL,
    weight NUMERIC(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    order_id INT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    shipment_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS routes (
    source_warehouse VARCHAR(50) NOT NULL,
    destination_warehouse VARCHAR(50) NOT NULL,
    distance INT NOT NULL,
    transport_cost INT NOT NULL,
    PRIMARY KEY (source_warehouse, destination_warehouse)
);

CREATE TABLE IF NOT EXISTS inventory (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL
);
