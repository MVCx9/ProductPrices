INSERT INTO BRANDS (ID, NAME) VALUES (1, 'ZARA');

INSERT INTO PRODUCTS (ID, NAME, DESCRIPTION) VALUES
(35455, 'Camiseta', 'Camiseta de manga corta'),
(35456, 'Gorro', 'Gorro de lana'),
(35457, 'Chaquetón', 'Chaquetón de invierno'),
(35458, 'Pantalones', 'Pantalones vaqueros');

INSERT INTO PRICES (PRICE_LIST, BRAND_ID, PRODUCT_ID, START_DATE, END_DATE, PRIORITY, PRICE, CURRENCY) VALUES
(1, 1, 35455, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35.50, 'EUR'),
(2, 1, 35455, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 'EUR'),
(3, 1, 35455, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 'EUR'),
(4, 1, 35455, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 38.95, 'EUR'),
(1, 1, 35456, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 50.50, 'EUR'),
(2, 1, 35456, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 100.45, 'EUR'),
(1, 1, 35457, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 3245.50, 'EUR'),
(2, 1, 35457, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 2342.45, 'EUR'),
(1, 1, 35458, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 33.50, 'EUR'),
(2, 1, 35458, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 0, 65.45, 'EUR');


