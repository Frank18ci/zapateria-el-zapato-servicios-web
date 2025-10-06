-- Servicio: Precios
-- Nota: variant_id referencia externa a Catalog.product_variant.id (sin FK cruzado)

DROP DATABASE IF EXISTS db_price_service;
CREATE DATABASE db_price_service;
use db_price_service;

CREATE TABLE price_list (
  id            BIGINT auto_increment PRIMARY KEY,
  name          TEXT NOT NULL UNIQUE,
  currency_code CHAR(3) NOT NULL, -- ISO 4217
  valid_from    DATE,
  valid_to      DATE
);

CREATE TABLE price (
  id            BIGINT auto_increment PRIMARY KEY,
  price_list_id BIGINT NOT NULL REFERENCES price_list(id) ON DELETE CASCADE,
  variant_id    BIGINT NOT NULL,          -- referencia externa (Catálogo.product_variant.id)
  unit_price    NUMERIC(12,2) NOT NULL CHECK (unit_price >= 0),
  UNIQUE (price_list_id, variant_id)
);

-- Índices
CREATE INDEX idx_price_variant ON price(variant_id);
CREATE INDEX idx_price_list_validity ON price_list(valid_from, valid_to);