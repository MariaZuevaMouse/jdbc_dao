
-- Create database structure
DROP TABLE IF EXISTS menu_items;
CREATE TABLE IF NOT EXISTS menu_items (
    id        BIGINT        PRIMARY KEY AUTOINCREMENT
                            NOT NULL,
    name VARCHAR (255) NOT NULL,
    price     INTEGER       NOT NULL
);

DROP TABLE IF EXISTS guest;
CREATE TABLE IF NOT EXISTS guest (
    id   INTEGER       PRIMARY KEY AUTOINCREMENT
                       NOT NULL,
    name VARCHAR (255) NOT NULL
);

DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders (
    id       INTEGER PRIMARY KEY AUTOINCREMENT
                     NOT NULL,
    order_date     DATETIME    NOT NULL,
    guest_id INTEGER REFERENCES Guest (ID)
);

DROP TABLE IF EXISTS order_vs_menu_items;
CREATE TABLE IF NOT EXISTS order_vs_menu_items (
    order_id    INTEGER NOT NULL
                       REFERENCES Orders (ID),
    menu_item_id INTEGER NOT NULL
                       REFERENCES MenuItems (ID),
    dish_count INTEGER NOT NULL
);

-- CRUD data queries
-- menu items
    INSERT INTO menu_items (name, price) VALUES (?, ?);
    SELECT * FROM menu_items WHERE name = (?);
    UPDATE menu_items SET price = (?) where name = (?);
    DELETE FROM menu_items WHERE id = (?);
--guest
    INSERT INTO guest (name) VALUES (?);
    SELECT * FROM guest WHERE name = (?);
    UPDATE guest SET name = (?) WHERE id = (?);
    DELETE FROM guest WHERE name = (?);
--orders
    INSERT INTO orders (order_date, guest_id) VALUES (?, ?);
    SELECT * FROM orders WHERE order_date = (?);
    SELECT * FROM orders WHERE guest_id =2 AND order_date= (SELECT MAX(order_date) FROM orders WHERE guest_id=2);
    SELECT * FROM orders WHERE guest_id =(?) AND order_date= (SELECT MAX(order_date) FROM orders WHERE guest_id=(?));
    UPDATE orders SET guest_id = (?) WHERE id = (?);
    DELETE FROM orders WHERE order_date = (?);
--order_vs_menu_items
    INSERT INTO order_vs_menu_items (order_id, menu_item_id, dish_count) VALUES (?, ?, ?);
    SELECT * FROM order_vs_menu_items WHERE order_id = (?) AND menu_item_id =(?);
    UPDATE order_vs_menu_items SET dish_count = (?) WHERE order_id = (?) AND menu_item_id = (?);
    DELETE FROM order_vs_menu_items WHERE order_id = (?) AND menu_item_id = (?);

--insert some test data

--statistics queries
SELECT * FROM order_vs_menu_items WHERE order_id = (?);