-- Create person table
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
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
    city_id INTEGER,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create user table
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    person_id INTEGER,
    FOREIGN KEY (person_id) REFERENCES person(id)
);

-- Create rol table
CREATE TABLE rol (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create module table
CREATE TABLE module (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create form table
CREATE TABLE form (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create permission table
CREATE TABLE permission (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create form_module table
CREATE TABLE form_module (
    id SERIAL PRIMARY KEY,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    form_id INTEGER NOT NULL,
    module_id INTEGER NOT NULL,
    FOREIGN KEY (form_id) REFERENCES form(id),
    FOREIGN KEY (module_id) REFERENCES module(id)
);

-- Create rol_user table
CREATE TABLE rol_user (
    id SERIAL PRIMARY KEY,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    rol_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES rol(id),
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);

-- Create rol_form_permit table
CREATE TABLE rol_form_permit (
    id SERIAL PRIMARY KEY,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    rol_id INTEGER NOT NULL,
    form_id INTEGER NOT NULL,
    permission_id INTEGER NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES rol(id),
    FOREIGN KEY (form_id) REFERENCES form(id),
    FOREIGN KEY (permission_id) REFERENCES permission(id)
);