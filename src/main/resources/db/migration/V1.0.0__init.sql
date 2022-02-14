create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    description varchar(255)
);


-- продукты
insert into products (title, price, description)
values ('Product1', 34, 'описалово'),
       ('Product2', 8340, 'blahblahblah'),
       ('Product3', 8345, 'описалово'),
       ('Product4', 650, ',блаблабла');

-- корзина
create table carts
(
    id_cart         bigserial primary key,
    user_id         int,
    product_id      varchar(255),
--     удалить после настройки связей таблиц
    product_name    varchar(255),
--     удалить после настройки связей таблиц
    cost            int,
--     удалить после настройки связей таблиц
    count           int,
    date_expiration date


);

-- insert into carts (user_id, product_id, product_name, cost, count)
-- values (1,1,'ewe',2,3),
--        (1,2,'reewe',2,3);
--


-- корзина_продукт связь

create table cart_to_product
(
    id_cart    int,
    id_product int,
    count      int
);

insert into cart_to_product (id_cart, id_product, count)
values (1, 1, 2),
       (1, 3, 5);
