# jdbc_dao

initial task:

Система Ресторан. Клиент осуществляет Заказ из Меню.
Что необходимо сделать:
1.   Создать базу данных в любой СУБД (MySQL, MS SQL, SQLite, PostreSQL и тд) на ваш вкус;
2.   Организовать доступ к базе данных из вашего приложения используя JDBC и шаблон DAO;
3.   Релизовать необходимые сервисы, которые будут каки-либо образом использовать данные из таблиц БД (просто получать, получать по параметру, получать в определённом порядке, считать статистику на основе полученных данных и тд);
4.   Реализовать вывод результатов работы сервисного слоя в консоль;
5.   Написать тесты для сервисного слоя (TestNG);
6.   Классы и интерфейсы должны быть хорошо структурированы по пакетам;
7.   Соблюдать Java Code Conventions;

Implementation:

1. Used MySQL database instance/ all database commands placed in file src/db/database_create2.sql;
2. Created 4 database entities Guest / Orders / MenuItem / OrderVsMenuItem;
3. Created DAO interfaces and their implementation classes (JDBC).
4. Created services to recieve different set of data from database;
5. Created tests for service layer with using TestNG test framework
