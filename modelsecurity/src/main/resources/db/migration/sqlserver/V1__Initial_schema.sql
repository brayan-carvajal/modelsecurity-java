-- Create person table
CREATE TABLE person (
    id INT IDENTITY(1,1) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    document_type VARCHAR(255),
    document VARCHAR(255),
    date_born DATE,
    phone_number VARCHAR(255),
    gender VARCHAR(255),
    person_exter VARCHAR(255),
    eps_id VARCHAR(255),
    second_last_name VARCHAR(255),
    middle_name VARCHAR(255),
    city_id INT,
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create user table
CREATE TABLE [user] (
    id INT IDENTITY(1,1) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    registration_date DATETIME2,
    is_deleted BIT NOT NULL DEFAULT 0,
    person_id INT,
    FOREIGN KEY (person_id) REFERENCES person(id)
);

-- Create rol table
CREATE TABLE rol (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create module table
CREATE TABLE module (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create form table
CREATE TABLE form (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create permission table
CREATE TABLE permission (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    is_deleted BIT NOT NULL DEFAULT 0
);

-- Create form_module table
CREATE TABLE form_module (
    id INT IDENTITY(1,1) PRIMARY KEY,
    is_deleted BIT NOT NULL DEFAULT 0,
    form_id INT NOT NULL,
    module_id INT NOT NULL,
    FOREIGN KEY (form_id) REFERENCES form(id),
    FOREIGN KEY (module_id) REFERENCES module(id)
);

-- Create rol_user table
CREATE TABLE rol_user (
    id INT IDENTITY(1,1) PRIMARY KEY,
    is_deleted BIT NOT NULL DEFAULT 0,
    rol_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES rol(id),
    FOREIGN KEY (user_id) REFERENCES [user](id)
);

-- Create rol_form_permit table
CREATE TABLE rol_form_permit (
    id INT IDENTITY(1,1) PRIMARY KEY,
    is_deleted BIT NOT NULL DEFAULT 0,
    rol_id INT NOT NULL,
    form_id INT NOT NULL,
    permission_id INT NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES rol(id),
    FOREIGN KEY (form_id) REFERENCES form(id),
    FOREIGN KEY (permission_id) REFERENCES permission(id)
);