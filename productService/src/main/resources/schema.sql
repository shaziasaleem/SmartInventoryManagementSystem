
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500),
    size VARCHAR(10),
    material VARCHAR(10),
    packaging VARCHAR(15),
    createdByUserId BIGSERIAL
);
