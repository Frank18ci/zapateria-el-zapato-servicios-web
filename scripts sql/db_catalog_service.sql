-- Servicio: Catálogo de Producto

-- Catálogos base
CREATE TABLE brand (
  id   BIGINT auto_increment PRIMARY KEY,
  name TEXT NOT NULL UNIQUE
);

CREATE TABLE category (
  id        BIGINT auto_increment PRIMARY KEY,
  name      TEXT NOT NULL,
  parent_id BIGINT REFERENCES category(id) ON DELETE SET NULL,
  UNIQUE (parent_id, name)
);

CREATE TABLE color (
  id   BIGINT auto_increment PRIMARY KEY,
  name TEXT NOT NULL UNIQUE,
  hex  CHAR(7)
);

CREATE TABLE width (
  id          BIGINT auto_increment PRIMARY KEY,
  code        VARCHAR(10) NOT NULL UNIQUE, -- N, M, W, D, E...
  description TEXT
);

CREATE TABLE size (
  id            BIGINT auto_increment PRIMARY KEY,
  scale_code    TEXT NOT NULL,            -- EU, US_M, US_W, UK, CM...
  code          TEXT NOT NULL,            -- "42", "8.5"
  numeric_value DECIMAL(6,2),
  mm_length     DECIMAL(6,2),
  UNIQUE (scale_code, code)
);

-- Modelo y variantes
CREATE TABLE shoe_model (
  id              BIGINT auto_increment PRIMARY KEY,
  brand_id        BIGINT NOT NULL REFERENCES brand(id),
  category_id     BIGINT NOT NULL REFERENCES category(id),
  gender_code     VARCHAR(10),            -- M, W, K, U (catálogo inline)
  code            TEXT NOT NULL,          -- código interno de la marca
  name            TEXT NOT NULL,
  description     TEXT,
  release_year    SMALLINT,
  discontinued_at DATETIME,
  UNIQUE (brand_id, code)
);

CREATE TABLE model_color (
  id         BIGINT auto_increment PRIMARY KEY,
  model_id   BIGINT NOT NULL REFERENCES shoe_model(id) ON DELETE CASCADE,
  color_id   BIGINT NOT NULL REFERENCES color(id),
  color_code TEXT, -- código de color del fabricante
  UNIQUE (model_id, color_id)
);

CREATE TABLE product_variant (
  id             BIGINT auto_increment PRIMARY KEY,
  model_color_id BIGINT NOT NULL REFERENCES model_color(id) ON DELETE RESTRICT,
  size_id        BIGINT NOT NULL REFERENCES size(id),
  width_id       BIGINT NOT NULL REFERENCES width(id),
  sku_code       TEXT NOT NULL UNIQUE,
  status         TEXT DEFAULT 'ACTIVE',
  UNIQUE (model_color_id, size_id, width_id)
);

-- Índices
CREATE INDEX idx_category_parent ON category(parent_id);
CREATE INDEX idx_model_brand_cat ON shoe_model(brand_id, category_id);
CREATE INDEX idx_model_color_model ON model_color(model_id);
CREATE INDEX idx_variant_combo ON product_variant(model_color_id, size_id, width_id);