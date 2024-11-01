
CREATE TABLE IF NOT EXISTS product (
    id SERIAL auto_increment PRIMARY KEY,
    product_name VARCHAR(255),
    product_type VARCHAR(255),
    price decimal (10,2) not null,
    quantity INT not null
);

insert into product(product_name, product_type, price, quantity)
values ('Spite','Beverage', 10.00, 10),
       ('Spite1','Beverage', 10.00, 100),
       ('Spite2','Beverage', 10.00, 200),
       ('Spite3','Beverage', 10.00, 300),
       ('Spite4','Beverage', 10.00, 400),
       ('Spite5','Beverage', 10.00, 500);