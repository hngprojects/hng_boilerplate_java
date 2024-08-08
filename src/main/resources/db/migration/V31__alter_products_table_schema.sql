--Added needed columns
ALTER TABLE products
ADD COLUMN current_stock INT,
ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN is_available BOOLEAN DEFAULT TRUE;
