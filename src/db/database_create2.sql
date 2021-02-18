
-- Create database structure
DROP TABLE IF EXISTS menu_items;
CREATE TABLE IF NOT EXISTS menu_items (
    id        INTEGER        PRIMARY KEY AUTOINCREMENT
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
    guest_id INTEGER REFERENCES Guest (id)
);

DROP TABLE IF EXISTS order_vs_menu_items;
CREATE TABLE IF NOT EXISTS order_vs_menu_items (
    order_id    INTEGER NOT NULL
                       REFERENCES orders (id),
    menu_item_id INTEGER NOT NULL
                       REFERENCES menu_items (id),
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

-- CREATE useful VIEWS
DROP VIEW combined_info;
CREATE VIEW combined_info AS SELECT * FROM order_vs_menu_items ovmi LEFT JOIN orders o ON o.id = ovmi.order_id LEFT JOIN menu_items mi ON ovmi.menu_item_id =mi.id LEFT JOIN guest gu ON o.guest_id = gu.id ;
SELECT * FROM  combined_info;

DROP VIEW join_all_tabels;
CREATE VIEW join_all_tabels AS SELECT * FROM order_vs_menu_items ovmi LEFT JOIN orders o ON o.id = ovmi.order_id LEFT JOIN guest gu ON o.guest_id = gu.id  LEFT JOIN menu_items mi ON ovmi.menu_item_id =mi.id ;
SELECT * FROM join_all_tabels;

--statistics queries
--guest table
SELECT * FROM guest;
SELECT * FROM guest ORDER BY name;
SELECT * FROM guest ORDER BY name DESC;
SELECT * FROM guest WHERE id = ?;

-- menu items table
SELECT * FROM menu_items ;
SELECT * FROM menu_items ORDER  BY name;
SELECT * FROM menu_items ORDER  BY name DESC;
-- popular
SELECT menu_item_id, SUM(dish_count) FROM order_vs_menu_items GROUP BY menu_item_id ORDER BY SUM(dish_count)  DESC limit 1 ;
--unpopular!
SELECT menu_item_id, SUM(dish_count) FROM order_vs_menu_items GROUP BY menu_item_id ORDER BY SUM(dish_count) limit 1;
SELECT * FROM menu_items WHERE price =(SELECT MAX(price) FROM menu_items);
SELECT * FROM menu_items WHERE price =(SELECT MIN(price) FROM menu_items);

-- orders data statistic

--  order's total price table
SELECT order_id, SUM(dish_count*price) as total_price , name FROM join_all_tabels GROUP BY order_id ;

SELECT * FROM orders;
SELECT * FROM orders WHERE id=?;
SELECT * FROM orders ORDER BY id DESC LIMIT 1;
SELECT * FROM orders WHERE order_date = ?;
-- cheapest order
SELECT order_id, SUM(dish_count*price) as total_price FROM combined_info GROUP BY order_id ORDER BY total_price LIMIT 1;
-- most expensive
SELECT order_id, SUM(dish_count*price) as total_price , name FROM join_all_tabels GROUP BY order_id ORDER BY total_price DESC LIMIT 1;
