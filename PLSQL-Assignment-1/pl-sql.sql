drop database library;
create database library;
use library;
create table BOOK(id int primary key auto_increment,
title varchar(255) not null,price double,author varchar(255),
publication_house varchar(255), category varchar(255), book_count int,
status varchar(255)
);
desc book;
INSERT INTO BOOK (title, price, author, publication_house, category, book_count, status) VALUES
('The Lost City', 250.50, 'John Carter', 'McGraw Hill', 'Fiction', 10, 'In Stock'),
('Battlefront', 320.00, 'Emily Stone', 'Dreamfolks', 'War', 5, 'Out-of-Stock'),
('Laugh Riot', 180.75, 'Tom Hardy', 'Warner Bros', 'Comedy', 15, 'In Stock'),
('Sports Glory', 400.00, 'Sarah Lane', 'McGraw Hill', 'Sports', 8, 'In Stock'),
('Dream Tales', 275.00, 'Olivia Smith', 'Dreamfolks', 'Fiction', 7, 'Out-of-Stock'),
('Heroes of War', 350.25, 'Liam Johnson', 'Warner Bros', 'War', 4, 'In Stock'),
('Comedy Nights', 200.00, 'Emma Davis', 'McGraw Hill', 'Comedy', 12, 'In Stock'),
('Game On', 390.00, 'Mason Clark', 'Dreamfolks', 'Sports', 6, 'Out-of-Stock'),
('Mystic Lands', 260.50, 'Ava Martin', 'Warner Bros', 'Fiction', 9, 'In Stock'),
('War Chronicles', 310.75, 'Noah Lewis', 'McGraw Hill', 'War', 3, 'Out-of-Stock'),
('Fun Unlimited', 210.20, 'Isabella Walker', 'Dreamfolks', 'Comedy', 14, 'In Stock'),
('Winning Moves', 430.00, 'Ethan Allen', 'Warner Bros', 'Sports', 5, 'In Stock'),
('Fantasy Realm', 280.60, 'Sophia Harris', 'McGraw Hill', 'Fiction', 8, 'In Stock'),
('Last Battle', 340.90, 'Logan Young', 'Dreamfolks', 'War', 2, 'Out-of-Stock'),
('Laugh Attack', 195.50, 'Mia Scott', 'Warner Bros', 'Comedy', 13, 'In Stock');
delimiter $$
create procedure proc_books_in_stock(in b_price double)
begin
select *
from book where b_price>price and status='In Stock';
end;
call proc_books_in_stock(200.50);
drop procedure proc_books_in_stock;

DELIMITER $$
CREATE PROCEDURE proc_delete_books(IN b_publication_house VARCHAR(255))
BEGIN
   DELETE book 
FROM book 
JOIN (SELECT id FROM book WHERE publication_house = b_publication_house) AS book2 
ON book.id = book2.id;
END $$
DELIMITER ;
call proc_delete_books('Warner Bros');
drop procedure proc_delete_books;

delimiter $$
CREATE PROCEDURE proc_update_books(in price_per int,IN b_category VARCHAR(255))
begin
UPDATE book JOIN (SELECT id FROM book WHERE category = b_category) AS
 book2 ON book.id = book2.id 
 SET book.price = book.price + (book.price * (price_per / 100));
end;
call proc_update_books(10,'comedy');



