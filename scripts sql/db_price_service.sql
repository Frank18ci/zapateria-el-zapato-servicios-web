-- Nota: variant_id referencia externa a Catalog.product_variant.id (sin FK cruzado)

DROP DATABASE IF EXISTS db_zapateria_price_service;
CREATE DATABASE db_zapateria_price_service;
use db_zapateria_price_service;
-- Servicio: Precios

CREATE TABLE price_list (
  id            BIGINT auto_increment PRIMARY KEY,
  name          VARCHAR(255) NOT NULL UNIQUE,
  currency_code CHAR(3) NOT NULL, -- ISO 4217
  valid_from    DATE,
  valid_to      DATE
);

insert into price_list (name, currency_code, valid_from, valid_to) values
('Precio Minorista', 'PEN', '2025-01-01', NULL),
('Precio Mayorista', 'PEN', '2025-01-01', NULL),
('Oferta Especial Verano 2025', 'USD', '2025-01-01', '2025-03-31');

CREATE TABLE price (
  id            BIGINT auto_increment PRIMARY KEY,
  price_list_id BIGINT NOT NULL REFERENCES price_list(id) ON DELETE CASCADE,
  variant_id    BIGINT NOT NULL,          -- referencia externa (Catálogo.product_variant.id)
  unit_price    DECIMAL(12,2) NOT NULL CHECK (unit_price >= 0),
  UNIQUE (price_list_id, variant_id)
);

insert into price (price_list_id, variant_id, unit_price) values
(1, 1, 150.00), -- Nike Air Max 270 Black - Retail
(1, 2, 150.00), -- Nike Air Max 270 White - Retail
(1, 3, 180.00), -- Adidas Ultraboost Red - Retail
(1, 4, 180.00), -- Adidas Ultraboost Blue - Retail
(2, 1, 120.00), -- Nike Air Max 270 Black - Wholesale
(2, 2, 120.00), -- Nike Air Max 270 White - Wholesale
(2, 3, 140.00), -- Adidas Ultraboost Red - Wholesale
(2, 4, 140.00), -- Adidas Ultraboost Blue - Wholesale
(3, 1, 130.00), -- Nike Air Max 270 Black - Summer Sale
(3, 2, 130.00); -- Nike Air Max 270 White - Summer Sale

-- Índices
CREATE INDEX idx_price_variant ON price(variant_id);
CREATE INDEX idx_price_list_validity ON price_list(valid_from, valid_to);