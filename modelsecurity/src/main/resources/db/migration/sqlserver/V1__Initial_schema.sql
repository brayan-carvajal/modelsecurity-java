-- Initial schema for SQL Server
CREATE TABLE person (
    id INT IDENTITY(1,1) PRIMARY KEY,
    first_name NVARCHAR(255) NOT NULL,
    last_name NVARCHAR(255) NOT NULL,
    document_type NVARCHAR(50),
    document NVARCHAR(50),
    date_born DATE,
    phone_number NVARCHAR(20),
    gender NVARCHAR(10),
    person_exter NVARCHAR(255),
    eps_id NVARCHAR(50),
    second_last_name NVARCHAR(255),
    middle_name NVARCHAR(255),
    city_id INT,
    is_deleted BIT NOT NULL DEFAULT 0
);

CREATE TABLE [user] (
    id INT IDENTITY(1,1) PRIMARY KEY,
    email NVARCHAR(255) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    registration_date DATETIME,
    is_deleted BIT NOT NULL DEFAULT 0,
    person_id INT,
    FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE rol (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    description NTEXT,
    is_deleted BIT NOT NULL DEFAULT 0
);

CREATE TABLE permission (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    description NTEXT,
    is_deleted BIT NOT NULL DEFAULT 0
);

CREATE TABLE form (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    description NTEXT,
    is_deleted BIT NOT NULL DEFAULT 0
);

CREATE TABLE module (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    description NTEXT,
    is_deleted BIT NOT NULL DEFAULT 0
);

CREATE TABLE form_module (
    id INT IDENTITY(1,1) PRIMARY KEY,
    is_deleted BIT NOT NULL DEFAULT 0,
    form_id INT NOT NULL,
    module_id INT NOT NULL,
    FOREIGN KEY (form_id) REFERENCES form(id),
    FOREIGN KEY (module_id) REFERENCES module(id)
);

CREATE TABLE rol_user (
    id INT IDENTITY(1,1) PRIMARY KEY,
    is_deleted BIT NOT NULL DEFAULT 0,
    rol_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES rol(id),
    FOREIGN KEY (user_id) REFERENCES [user](id)
);

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