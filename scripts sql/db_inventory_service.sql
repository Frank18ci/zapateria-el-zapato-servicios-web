DROP DATABASE IF EXISTS db_inventory_service;
CREATE DATABASE db_inventory_service;
use db_inventory_service;
-- Servicio: Inventario
-- Nota: variant_id referencia externa a Catalog.product_variant.id (sin FK cruzado)

CREATE TABLE warehouse (
  id      BIGINT auto_increment PRIMARY KEY,
  code    TEXT NOT NULL UNIQUE,
  name    TEXT NOT NULL,
  address TEXT
);

CREATE TABLE stock_movement (
  id             BIGINT auto_increment PRIMARY KEY,
  variant_id     BIGINT NOT NULL,            -- referencia externa (Catálogo.product_variant.id)
  warehouse_id   BIGINT NOT NULL REFERENCES warehouse(id),
  bin_code       TEXT,                       -- ubicación libre
  movement_type  VARCHAR(30) NOT NULL,       -- RECEIPT, ISSUE, ADJUSTMENT, TRANSFER_IN, TRANSFER_OUT
  quantity       INT NOT NULL CHECK (quantity <> 0),
  unit_cost      NUMERIC(12,2),
  currency_code  CHAR(3),
  created_at     DATETIME NOT NULL DEFAULT NOW(),
  reason         TEXT,
  ref_doc        TEXT,
  CHECK (movement_type IN ('RECEIPT','ISSUE','ADJUSTMENT','TRANSFER_IN','TRANSFER_OUT'))
);

-- Índices
CREATE INDEX idx_movement_variant_wh ON stock_movement(variant_id, warehouse_id, created_at);