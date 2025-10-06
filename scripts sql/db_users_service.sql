DROP DATABASE IF EXISTS db_user_service;
CREATE DATABASE db_user_service;
use db_user_service;

CREATE TABLE users (
  id             BIGINT auto_increment PRIMARY KEY,
  email          TEXT NOT NULL UNIQUE,
  username       TEXT UNIQUE,
  password_hash  TEXT NOT NULL,
  full_name      TEXT,
  status         TEXT NOT NULL DEFAULT 'ACTIVE',
  created_at     DATETIME NOT NULL DEFAULT NOW(),
  updated_at     DATETIME NOT NULL DEFAULT NOW(),
  CHECK (status IN ('ACTIVE','INACTIVE','LOCKED','PENDING'))
);

CREATE TABLE role (
  id          BIGINT auto_increment  PRIMARY KEY,
  code        VARCHAR(50) NOT NULL UNIQUE, -- e.g., ADMIN, MANAGER, OPERATOR, VIEWER
  name        TEXT NOT NULL,
  description TEXT
);

CREATE TABLE user_role (
  user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  role_id BIGINT NOT NULL REFERENCES role(id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role_id)
);

-- Índices útiles
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_user_role_user ON user_role(user_id);
CREATE INDEX idx_user_role_role ON user_role(role_id);