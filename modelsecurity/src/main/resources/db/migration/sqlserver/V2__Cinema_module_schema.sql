-- Create movie table
CREATE TABLE movie (
    id INT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    duration INT,
    release_date DATE,
    genre VARCHAR(255),
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create customer table
CREATE TABLE customer (
    id INT IDENTITY(1,1) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255),
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create employee table
CREATE TABLE employee (
    id INT IDENTITY(1,1) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    position VARCHAR(255),
    hire_date DATE,
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create theater_room table
CREATE TABLE theater_room (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    capacity INT,
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create shift table
CREATE TABLE shift (
    id INT IDENTITY(1,1) PRIMARY KEY,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    shift_date DATE NOT NULL,
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create screening table
CREATE TABLE screening (
    id INT IDENTITY(1,1) PRIMARY KEY,
    movie_id INT NOT NULL,
    theater_room_id INT NOT NULL,
    start_time DATETIME2 NOT NULL,
    end_time DATETIME2 NOT NULL,
    is_deleted BIT NOT NULL DEFAULT 0,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (theater_room_id) REFERENCES theater_room(id)
);

-- Create reservation table
CREATE TABLE reservation (
    id INT IDENTITY(1,1) PRIMARY KEY,
    customer_id INT NOT NULL,
    screening_id INT NOT NULL,
    reservation_date DATETIME2 NOT NULL,
    status VARCHAR(255),
    is_deleted BIT NOT NULL DEFAULT 0,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (screening_id) REFERENCES screening(id)
);

-- Create employee_shift table
CREATE TABLE employee_shift (
    id INT IDENTITY(1,1) PRIMARY KEY,
    employee_id INT NOT NULL,
    shift_id INT NOT NULL,
    is_deleted BIT NOT NULL DEFAULT 0,
    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (shift_id) REFERENCES shift(id)
);

-- Create screening_employee table
CREATE TABLE screening_employee (
    id INT IDENTITY(1,1) PRIMARY KEY,
    screening_id INT NOT NULL,
    employee_id INT NOT NULL,
    is_deleted BIT NOT NULL DEFAULT 0,
    FOREIGN KEY (screening_id) REFERENCES screening(id),
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);