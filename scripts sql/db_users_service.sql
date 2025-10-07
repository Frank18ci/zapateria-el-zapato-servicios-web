DROP DATABASE IF EXISTS db_zapateria_user_service;
CREATE DATABASE db_zapateria_user_service;
use db_zapateria_user_service;

CREATE TABLE users (
  id             BIGINT auto_increment PRIMARY KEY,
  email          VARCHAR(100) NOT NULL UNIQUE,
  username       VARCHAR(50) UNIQUE,
  password_hash  VARCHAR(255) NOT NULL,
  full_name      VARCHAR(100),
  status         VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
  created_at     DATETIME NOT NULL DEFAULT NOW(),
  updated_at     DATETIME NOT NULL DEFAULT NOW() on update now(),
  CHECK (status IN ('ACTIVE','INACTIVE','LOCKED','PENDING'))
);

insert into users (email, username, password_hash, full_name, status) values
('john.doe@example.com', 'johndoe', 'hashed_password_1', 'John Doe', 'ACTIVE'),
('jane.smith@example.com', 'janesmith', 'hashed_password_2', 'Jane Smith', 'ACTIVE');

CREATE TABLE role (
  id          BIGINT auto_increment  PRIMARY KEY,
  code        VARCHAR(50) NOT NULL UNIQUE, -- e.g., ADMIN, MANAGER, OPERATOR, VIEWER
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(255)
);

insert into role (code, name, description) values
('ADMIN', 'Administrator', 'Acceso total a todas las funciones y configuraciones del sistema.'),
('VIEWER', 'Viewer', 'Acceso solo de lectura a la información de productos y precios.');

CREATE TABLE user_role (
  user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  role_id BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role_id)
);

-- Asignación de roles a usuarios
insert into user_role (user_id, role_id) values
(1, 1), -- John Doe es ADMIN
(2, 2); -- Jane Smith es VIEWER

-- Índices útiles
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_user_role_user ON user_role(user_id);
CREATE INDEX idx_user_role_role ON user_role(role_id);