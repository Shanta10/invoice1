CREATE TABLE IF NOT EXISTS client (
    id SERIAL,
    nui VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    address VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS invoice (
    id SERIAL,
    code VARCHAR(255) NOT NULL,
    create_at DATE NOT NULL,
    total VARCHAR(20) NOT NULL,
    client_id INT NOT NULL, -- Se agrega la columna client_id
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES client(id)
    );

CREATE TABLE IF NOT EXISTS product (
    id SERIAL,
    description VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    stock INT,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS detail (
    id SERIAL,
    quantity INT NOT NULL,
    price FLOAT NOT NULL,
    invoice_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
    );
