-- Create movie table
CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    duration INTEGER,
    release_date DATE,
    genre VARCHAR(255),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create customer table
CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create employee table
CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    position VARCHAR(255),
    hire_date DATE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create theater_room table
CREATE TABLE theater_room (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    capacity INTEGER,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create shift table
CREATE TABLE shift (
    id SERIAL PRIMARY KEY,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    shift_date DATE NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create screening table
CREATE TABLE screening (
    id SERIAL PRIMARY KEY,
    movie_id INTEGER NOT NULL,
    theater_room_id INTEGER NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (theater_room_id) REFERENCES theater_room(id)
);

-- Create reservation table
CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    screening_id INTEGER NOT NULL,
    reservation_date TIMESTAMP NOT NULL,
    status VARCHAR(255),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (screening_id) REFERENCES screening(id)
);

-- Create employee_shift table
CREATE TABLE employee_shift (
    id SERIAL PRIMARY KEY,
    employee_id INTEGER NOT NULL,
    shift_id INTEGER NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (shift_id) REFERENCES shift(id)
);

-- Create screening_employee table
CREATE TABLE screening_employee (
    id SERIAL PRIMARY KEY,
    screening_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (screening_id) REFERENCES screening(id),
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);