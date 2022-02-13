create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    description varchar(255)
);

insert into products (title, price, description)
values ('Product1', 34, 'описалово'),
       ('Product2', 8340, 'blahblahblah'),
       ('Product3', 8345, 'описалово'),
       ('Product4', 650, ',блаблабла');


create table carts
(
    id_cart     bigserial primary key,
    user_id     int,
    product_id  varchar(255),
    count int
);

insert into carts (user_id, product_id,count)
values
       (1,1,3),
       (1,2,3);


