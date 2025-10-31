DROP DATABASE IF EXISTS db_zapateria_inventory_service;
CREATE DATABASE db_zapateria_inventory_service;
use db_zapateria_inventory_service;
-- Servicio: Inventario
-- Nota: variant_id referencia externa a Catalog.product_variant.id (sin FK cruzado)

CREATE TABLE warehouse (
  id      BIGINT auto_increment PRIMARY KEY,
  code    VARCHAR(50) NOT NULL UNIQUE,
  name    VARCHAR(100) NOT NULL,
  address VARCHAR(255)
);

insert into warehouse (code, name, address) values
('WH-001', 'Almacén Central', '123 Calle Principal, Arequipa, Perú'),
('WH-002', 'Almacén Norte', '456 Avenida Norte, Arequipa, Perú'),
('WH-003', 'Almacén Sur', '789 Boulevard Sur, Arequipa, Perú');

CREATE TABLE stock_movement (
id             BIGINT auto_increment PRIMARY KEY,
variant_id     BIGINT NOT NULL,            -- external reference (Catalog.product_variant.id)
warehouse_id   BIGINT NOT NULL REFERENCES warehouse(id),
bin_code       VARCHAR(50),                -- free location
movement_type  VARCHAR(30) NOT NULL,       -- RECEIPT, ISSUE, ADJUSTMENT, TRANSFER_IN, TRANSFER_OUT
quantity       INT NOT NULL CHECK (quantity <> 0),
unit_cost      DECIMAL(12,2),
currency_code  CHAR(3),
created_at     DATETIME NOT NULL DEFAULT NOW(),
reason         VARCHAR(255),
ref_doc        VARCHAR(255),
CHECK (movement_type IN ('RECEIPT','ISSUE','ADJUSTMENT','TRANSFER_IN','TRANSFER_OUT'))
);

INSERT INTO stock_movement
(variant_id, warehouse_id, bin_code, movement_type, quantity, unit_cost, currency_code, created_at, reason, ref_doc)
VALUES
(1, 1, 'A1', 'RECEIPT', 100, 75.00, 'PEN', '2023-10-01 10:00:00', 'Stock inicial', 'OC-1001'),
(2, 1, 'A1', 'RECEIPT', 150, 80.00, 'PEN', '2023-10-01 11:00:00', 'Stock inicial', 'OC-1002'),
(3, 2, 'B2', 'RECEIPT', 200, 90.00, 'PEN', '2023-10-02 09:30:00', 'Stock inicial', 'OC-1003'),
(4, 2, 'B2', 'RECEIPT', 120, 85.00, 'PEN', '2023-10-02 10:15:00', 'Stock inicial', 'OC-1004'),
(5, 3, 'C3', 'RECEIPT', 180, 95.00, 'PEN', '2023-10-03 14:45:00', 'Stock inicial', 'OC-1005'),
(1, 1, 'A1', 'ISSUE', -20, NULL, NULL, '2023-10-05 15:00:00', 'Pedido de cliente #PV-2001', 'PV-2001'),
(2, 1, 'A1', 'ISSUE', -30, NULL, NULL, '2023-10-05 16:30:00', 'Pedido de cliente #PV-2002', 'PV-2002'),
(3, 2, 'B2', 'ISSUE', -25, NULL, NULL, '2023-10-06 11:20:00', 'Pedido de cliente #PV-2003', 'PV-2003'),
(4, 2, 'B2', 'ADJUSTMENT', -5, NULL, NULL, '2023-10-07 09:00:00', 'Ajuste por conteo de inventario', NULL),
(5, 3, 'C3', 'ADJUSTMENT', 10, NULL, NULL, '2023-10-07 10:30:00', 'Ajuste por conteo de inventario', NULL),
(1, 1, 'A1', 'TRANSFER_OUT', -15, NULL, NULL, '2023-10-08 13:00:00', 'Transferencia al almacén WH-002', 'TR-3001'),
(1, 2, 'B2', 'TRANSFER_IN', 15, NULL, NULL, '2023-10-08 13:30:00', 'Transferencia desde el almacén WH-001', 'TR-3001');

-- Índices
CREATE INDEX idx_movement_variant_wh ON stock_movement(variant_id, warehouse_id, created_at);