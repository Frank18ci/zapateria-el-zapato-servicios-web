-- Servicio: Catálogo de Producto
DROP DATABASE IF EXISTS db_zapateria_catalog_service;
CREATE DATABASE db_zapateria_catalog_service;
use db_zapateria_catalog_service;
-- Catálogos base
CREATE TABLE brand (
  id   BIGINT auto_increment PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

insert into brand (name) values
('Nike'),
('Adidas'),
('Puma'),
('Reebok'),
('New Balance'),
('Asics'),
('Under Armour'),
('Skechers'),
('Vans'),
('Converse');

CREATE TABLE category (
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR(50) NOT NULL,
  parent_id BIGINT NULL,
  UNIQUE (parent_id, name),
  FOREIGN KEY (parent_id) REFERENCES category(id) ON DELETE SET NULL
);

insert into category (name, parent_id) values
('Calzado', NULL),
('Entrenamiento', NULL),
('Casual', NULL),
('Baloncesto', NULL),
('Fútbol', NULL),
('Tenis', NULL),
('Senderismo', NULL),
('Sandalias', NULL),
('Botas', NULL),
('Estilo de vida', NULL),
('Ropa', NULL),
('Accesorios', NULL);

insert into category (name, parent_id) values
('Correr en carretera', 1),
('Correr en montaña', 1),
('Entrenamiento cruzado', 2),
('Levantamiento de pesas', 2),
('Chanclas', 8),
('Sandalias de dedo', 8);


CREATE TABLE color (
  id   BIGINT auto_increment PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE,
  hex  CHAR(7)
);

insert into color (name, hex) values
('Negro', '#000000'),
('Blanco', '#FFFFFF'),
('Rojo', '#FF0000'),
('Azul', '#0000FF'),
('Verde', '#00FF00'),
('Amarillo', '#FFFF00'),
('Naranja', '#FFA500'),
('Rosa', '#FFC0CB'),
('Morado', '#800080'),
('Gris', '#808080'),
('Marrón', '#A52A2A');

CREATE TABLE width (
  id          BIGINT auto_increment PRIMARY KEY,
  code        VARCHAR(10) NOT NULL UNIQUE, -- N, M, W, D, E...
  description VARCHAR(100)
);

insert into width (code, description) values
('N', 'Estrecho'),
('M', 'Mediano'),
('W', 'Ancho'),
('D', 'Profundo'),
('E', 'Extra Ancho');

CREATE TABLE size (
  id            BIGINT auto_increment PRIMARY KEY,
  scale_code    VARCHAR(10) NOT NULL,            -- EU, US_M, US_W, UK, CM...
  code          VARCHAR(10) NOT NULL,            -- "42", "8.5"
  numeric_value DECIMAL(10,2),
  mm_length     DECIMAL(10,2),
  UNIQUE (scale_code, code)
);

insert into size (scale_code, code, numeric_value, mm_length) values
('EU', '42', 42, 266),
('US_M', '8.5', 8.5, 273),
('US_W', '10', 10, 261),
('UK', '8', 8, 253),
('CM', '26', 26, 260);

-- Modelo y variantes
CREATE TABLE shoe_model (
  id              BIGINT auto_increment PRIMARY KEY,
  brand_id        BIGINT NOT NULL REFERENCES brand(id),
  category_id     BIGINT NOT NULL REFERENCES category(id),
  gender_code     VARCHAR(10),            -- M, W, K, U (catálogo inline)
  code            VARCHAR(50) NOT NULL,          -- código interno de la marca
  name            VARCHAR(100) NOT NULL,
  description     VARCHAR(255),
  release_year    SMALLINT,
  discontinued_at DATETIME,
  UNIQUE (brand_id, code)
);

insert into shoe_model (brand_id, category_id, gender_code, code, name, description, release_year, discontinued_at) values
(1, 1, 'M', 'NIKE-AIRMAX-270', 'Nike Air Max 270', 'Zapatillas de running con amortiguación visible en el talón.', 2018, NULL),
(2, 2, 'W', 'ADIDAS-ULTRABOOST', 'Adidas Ultraboost', 'Zapatillas de entrenamiento con tecnología Boost para mayor retorno de energía.', 2015, NULL),
(3, 3, 'K', 'PUMA-IGNITE', 'Puma Ignite', 'Zapatillas casuales con espuma Ignite para comodidad durante todo el día.', 2016, NULL),
(4, 4, 'U', 'REEBOK-NANO-X', 'Reebok Nano X', 'Zapatillas de entrenamiento cruzado diseñadas para estabilidad y soporte.', 2019, NULL),
(5, 1, 'M', 'NEWBALANCE-990V5', 'New Balance 990v5', 'Zapatillas de running clásicas con una mezcla de estilo y rendimiento.', 2019, NULL),
(1, 5, 'W', 'NIKE-COURT-ROYALE', 'Nike Court Royale', 'Zapatillas de tenis con un diseño retro y durabilidad en la cancha.', 2017, NULL),
(2, 6, 'K', 'ADIDAS-CLIMACOOL', 'Adidas Climacool', 'Zapatillas de tenis con ventilación avanzada para mantener los pies frescos.', 2014, NULL),
(3, 7, 'U', 'PUMA-FAAS-300', 'Puma Faas 300', 'Zapatillas de running ligeras y rápidas para corredores de todos los niveles.', 2013, NULL),
(4, 8, 'M', 'REEBOK-SLIDE', 'Reebok Slide', 'Chanclas cómodas y duraderas para uso diario y en la piscina.', 2020, NULL),
(5, 9, 'W', 'NEWBALANCE-754', 'New Balance 754', 'Botas de senderismo resistentes con soporte adicional para terrenos difíciles.', 2018, NULL);

CREATE TABLE model_color (
  id         BIGINT auto_increment PRIMARY KEY,
  model_id   BIGINT NOT NULL REFERENCES shoe_model(id) ON DELETE CASCADE,
  color_id   BIGINT NOT NULL REFERENCES color(id),
  color_code VARCHAR(10), -- código de color del fabricante
  UNIQUE (model_id, color_id)
);

insert into model_color (model_id, color_id, color_code) values
(1, 1, 'BLK'), -- Nike Air Max 270
(1, 2, 'WHT'), -- Nike Air Max 270
(2, 3, 'RED'), -- Adidas Ultraboost
(2, 4, 'BLU'), -- Adidas Ultraboost
(3, 5, 'GRN'), -- Puma Ignite
(3, 6, 'YLW'), -- Puma Ignite
(4, 7, 'ORG'), -- Reebok Nano X
(4, 8, 'PNK'), -- Reebok Nano X
(5, 9, 'PUR'), -- New Balance 990v5
(5, 10,'GRY'), -- New Balance 990v5
(6, 1, 'BLK'), -- Nike Court Royale
(6, 2, 'WHT'), -- Nike Court Royale
(7, 3, 'RED'), -- Adidas Climacool
(7, 4, 'BLU'), -- Adidas Climacool
(8, 5, 'GRN'), -- Puma Faas 300
(8, 6, 'YLW'), -- Puma Faas 300
(9, 7, 'ORG'), -- Reebok Slide
(9, 8, 'PNK'), -- Reebok Slide
(10,9, 'PUR'), -- New Balance 754
(10,10,'GRY'); -- New Balance 754

CREATE TABLE product_variant (
  id             BIGINT auto_increment PRIMARY KEY,
  model_color_id BIGINT NOT NULL REFERENCES model_color(id) ON DELETE RESTRICT,
  size_id        BIGINT NOT NULL REFERENCES size(id),
  width_id       BIGINT NOT NULL REFERENCES width(id),
  sku_code       VARCHAR(50) NOT NULL UNIQUE,
  status         VARCHAR(20) DEFAULT 'ACTIVE',
  UNIQUE (model_color_id, size_id, width_id)
);

insert into product_variant (model_color_id, size_id, width_id, sku_code, status) values
(1, 1, 2, 'NIKE-AIRMAX-270-BLK-42-M', 'ACTIVE'), -- Nike Air Max Black
(1, 2, 2, 'NIKE-AIRMAX-270-WHT-8.5-M', 'ACTIVE'), -- Nike Air Max White
(2, 3, 2, 'ADIDAS-ULTRABOOST-RED-10-W', 'ACTIVE'), -- Adidas Ultraboost Red
(2, 4, 2, 'ADIDAS-ULTRABOOST-BLU-8-M', 'ACTIVE'), -- Adidas Ultraboost Blue
(3, 5, 3, 'PUMA-IGNITE-GRN-26-M', 'ACTIVE'), -- Puma Ignite Green
(3, 1, 3, 'PUMA-IGNITE-YLW-42-W', 'ACTIVE'), -- Puma Ignite Yellow
(4, 2, 2, 'REEBOK-NANO-X-ORG-8.5-M', 'ACTIVE'), -- Reebok Nano X Orange
(4, 3, 2, 'REEBOK-NANO-X-PNK-10-W', 'ACTIVE'), -- Reebok Nano X Pink
(5, 4, 4, 'NEWBALANCE-990V5-PUR-42-M', 'ACTIVE'), -- New Balance 990v5 Purple
(5, 5, 4, 'NEWBALANCE-990V5-GRY-8.5-W', 'ACTIVE'); -- New Balance 990v5 Gray

-- Índices
CREATE INDEX idx_category_parent ON category(parent_id);
CREATE INDEX idx_model_brand_cat ON shoe_model(brand_id, category_id);
CREATE INDEX idx_model_color_model ON model_color(model_id);
CREATE INDEX idx_variant_combo ON product_variant(model_color_id, size_id, width_id);