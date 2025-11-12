-- Create movie table
CREATE TABLE movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    duration INT,
    release_date DATE,
    genre VARCHAR(255),
    is_deleted TINYINT(1) NOT NULL DEFAULT 0
);

-- Create customer table
CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255),
    is_deleted TINYINT(1) NOT NULL DEFAULT 0
);

-- Create employee table
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    position VARCHAR(255),
    hire_date DATE,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0
);

-- Create theater_room table
CREATE TABLE theater_room (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    capacity INT,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0
);

-- Create shift table
CREATE TABLE shift (
    id INT AUTO_INCREMENT PRIMARY KEY,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    shift_date DATE NOT NULL,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0
);

-- Create screening table
CREATE TABLE screening (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    theater_room_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (theater_room_id) REFERENCES theater_room(id)
);

-- Create reservation table
CREATE TABLE reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    screening_id INT NOT NULL,
    reservation_date DATETIME NOT NULL,
    status VARCHAR(255),
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (screening_id) REFERENCES screening(id)
);

-- Create employee_shift table
CREATE TABLE employee_shift (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    shift_id INT NOT NULL,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (shift_id) REFERENCES shift(id)
);

-- Create screening_employee table
CREATE TABLE screening_employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    screening_id INT NOT NULL,
    employee_id INT NOT NULL,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,
    FOREIGN KEY (screening_id) REFERENCES screening(id),
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);