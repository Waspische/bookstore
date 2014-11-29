DROP TABLE IF EXISTS ORDER_LINES;
DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS MAILING_ADDRESSES;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;

-- CREATE TABLES
CREATE TABLE IF NOT EXISTS USERS (LOGIN VARCHAR(30) NOT NULL, EMAIL VARCHAR(80), PWD VARCHAR(20) NOT NULL, PERSONNAL_ADR_ID INTEGER, PRIMARY KEY (LOGIN));
CREATE TABLE IF NOT EXISTS MAILING_ADDRESSES (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, LINE3 VARCHAR(80), ZIP VARCHAR(10), LINE1 VARCHAR(80) NOT NULL, LINE2 VARCHAR(80), CITY VARCHAR(45), PRIMARY KEY (ID));

CREATE TABLE IF NOT EXISTS AUTHORS (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, LAST_NAME VARCHAR(35) NOT NULL, BIRTH_DATE DATE, FIRST_NAME VARCHAR(35) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE IF NOT EXISTS BOOKS (ISBN13 VARCHAR(17) NOT NULL, TITLE VARCHAR(50) NOT NULL, EDITOR VARCHAR(20), UNIT_PRICE FLOAT, AUTHOR_ID INTEGER, PRIMARY KEY (ISBN13));

CREATE TABLE IF NOT EXISTS ORDERS (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, ORDER_DATE TIMESTAMP, USER_ID VARCHAR(30), SHIPPING_ADR_ID INTEGER, PRIMARY KEY (ID));
CREATE TABLE IF NOT EXISTS ORDER_LINES (QUANTITY INTEGER NOT NULL, ORDERS_ID INTEGER NOT NULL, BOOKS_ID VARCHAR(17) NOT NULL, PRIMARY KEY (ORDERS_ID, BOOKS_ID));

-- ADD FK CONSTRAINTS
ALTER TABLE USERS ADD CONSTRAINT IF NOT EXISTS SERSPERSONNALADRID FOREIGN KEY (PERSONNAL_ADR_ID) REFERENCES MAILING_ADDRESSES (ID);

ALTER TABLE BOOKS ADD CONSTRAINT IF NOT EXISTS FK_BOOKS_AUTHOR_ID FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS (ID);

ALTER TABLE ORDERS ADD CONSTRAINT IF NOT EXISTS FK_ORDERS_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS (LOGIN);
ALTER TABLE ORDERS ADD CONSTRAINT IF NOT EXISTS RDERSSHIPPINGADRID FOREIGN KEY (SHIPPING_ADR_ID) REFERENCES MAILING_ADDRESSES (ID);

ALTER TABLE ORDER_LINES ADD CONSTRAINT IF NOT EXISTS ORDERLINESORDERSID FOREIGN KEY (ORDERS_ID) REFERENCES ORDERS (ID);
ALTER TABLE ORDER_LINES ADD CONSTRAINT IF NOT EXISTS ORDERLINESBOOKS_ID FOREIGN KEY (BOOKS_ID) REFERENCES BOOKS (ISBN13);

--POPULATE SOME DATA	
--addresses
insert into MAILING_ADDRESSES (ID, LINE1, ZIP, CITY) VALUES (1, '666, rue des tekos', '59790', 'RONCHIN') ;
insert into MAILING_ADDRESSES (ID, LINE1, ZIP, CITY) VALUES (2, '7777, avenue sans fin', '59790', 'RONCHIN');
ALTER TABLE MAILING_ADDRESSES ALTER COLUMN id RESTART WITH 3;

--users
insert into USERS (LOGIN, EMAIL, PWD, PERSONNAL_ADR_ID) VALUES ('yhovart', 'yhovart@gmail.com', 'yh0vart', 1);

-- authors
insert into AUTHORS (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Merick', 'Schincariol', '1981-12-28') ;
insert into AUTHORS (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Mike', 'Keith', '1978-01-01');
insert into AUTHORS (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Ray', 'Bradbury', '1920-08-22');
insert into AUTHORS (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Edgar Allan', 'Poe', '1809-01-19');
insert into AUTHORS (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Charles', 'Baudelaire', '1821-04-09');
insert into AUTHORS (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Francois-Xavier', 'Sennesal', null);
insert into AUTHORS (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Peter', 'Tomasi', null);

-- books
insert into BOOKS (ISBN13, TITLE, UNIT_PRICE, EDITOR, AUTHOR_ID)
VALUES ('978-1430219569', 'Pro JPA 2: Mastering the Java Persistence API', 40.84, 'APress', 
(SELECT MIN(ID) FROM AUTHORS WHERE LAST_NAME='Schincariol'));

insert into BOOKS (ISBN13, TITLE, UNIT_PRICE, EDITOR, AUTHOR_ID)
VALUES ('978-1932394887', 'Java Persistence with Hibernate', 47.03, 'Manning Publications', 
(SELECT MIN(ID) FROM AUTHORS WHERE LAST_NAME='Keith'));

insert into BOOKS (ISBN13, TITLE, UNIT_PRICE, EDITOR, AUTHOR_ID)
VALUES ('978-2070415731', 'Fahrenheit 451', 5.04, 'Gallimard', 
(SELECT MIN(ID) FROM AUTHORS WHERE LAST_NAME='Bradbury'));

insert into BOOKS (ISBN13, TITLE, UNIT_PRICE, EDITOR, AUTHOR_ID)
VALUES ('978-2841030385', 'Le Corbeau', 14, 'William Blake', 
(SELECT MIN(ID) FROM AUTHORS WHERE LAST_NAME='Poe')) ;

insert into BOOKS (ISBN13, TITLE, UNIT_PRICE, EDITOR, AUTHOR_ID)
VALUES ('978-2290340868', 'Le Spleen de Paris', 1.9, 'Librio', 
(SELECT MIN(ID) FROM AUTHORS WHERE LAST_NAME='Baudelaire'));

insert into BOOKS (ISBN13, TITLE, UNIT_PRICE, EDITOR, AUTHOR_ID)
VALUES ('978-2035861566', 'Les fleurs du mal', 3.33, 'Larousse ', 
(SELECT MIN(ID) FROM AUTHORS WHERE LAST_NAME='Baudelaire'));

insert into BOOKS (ISBN13, TITLE, UNIT_PRICE, EDITOR, AUTHOR_ID)
VALUES ('978-2746054646', 'JSF mis en pratique avec Eclipse', 32.76, 'Editions ENI', 
(SELECT MIN(ID) FROM AUTHORS WHERE LAST_NAME='Sennesal'));

insert into BOOKS (ISBN13, TITLE, UNIT_PRICE, EDITOR, AUTHOR_ID)
VALUES ('978-2365773553', 'Batman et Robin Tome 1', 17.50, 'Urban Comics', 
(SELECT MIN(ID) FROM AUTHORS WHERE LAST_NAME='Tomasi'));

-- orders
insert into ORDERS (ID, ORDER_DATE, USER_ID, SHIPPING_ADR_ID) VALUES (1, CURRENT_TIMESTAMP, 'yhovart', 1);
insert into ORDERS (ID, ORDER_DATE, USER_ID, SHIPPING_ADR_ID) VALUES (2, CURRENT_TIMESTAMP, 'yhovart', 2);
ALTER TABLE ORDERS ALTER COLUMN id RESTART WITH 3;

--order lines
insert into ORDER_LINES (ORDERS_ID, BOOKS_ID, QUANTITY) VALUES (1, '978-2746054646', 6);
insert into ORDER_LINES (ORDERS_ID, BOOKS_ID, QUANTITY) VALUES (1, '978-1932394887', 6);
insert into ORDER_LINES (ORDERS_ID, BOOKS_ID, QUANTITY) VALUES (1, '978-1430219569', 1);

insert into ORDER_LINES (ORDERS_ID, BOOKS_ID, QUANTITY) VALUES (2, '978-2070415731', 1);
insert into ORDER_LINES (ORDERS_ID, BOOKS_ID, QUANTITY) VALUES (2, '978-2841030385', 1);
insert into ORDER_LINES (ORDERS_ID, BOOKS_ID, QUANTITY) VALUES (2, '978-2290340868', 1);
insert into ORDER_LINES (ORDERS_ID, BOOKS_ID, QUANTITY) VALUES (2, '978-2035861566', 1);

-- checks
select * from USERS usr LEFT OUTER JOIN MAILING_ADDRESSES adr on usr.PERSONNAL_ADR_ID=adr.ID;

select * from BOOKS bk left outer join AUTHORS aut on bk.AUTHOR_ID=aut.ID;

select usr.LOGIN, ord.ID, ord.ORDER_DATE, bk.TITLE, lin.QUANTITY, adr.LINE1, adr.ZIP, adr.CITY  
from ORDERS ord 
inner join ORDER_LINES lin on lin.ORDERS_ID=ord.ID
inner join BOOKS bk on bk.ISBN13=lin.BOOKS_ID
inner join USERS usr on ord.USER_ID=usr.LOGIN
inner join MAILING_ADDRESSES adr on adr.ID=ord.SHIPPING_ADR_ID
ORDER BY usr.LOGIN, ord.ORDER_DATE, ord.ID, bk.TITLE;

