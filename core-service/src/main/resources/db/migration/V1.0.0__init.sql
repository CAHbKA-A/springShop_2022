create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       DECIMAL,
    description varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);


-- продукты
insert into products (title, price, description)
values ('Product1', 41.76, 'описалово'),
       ('Product2', 10.54, 'blahblahblah'),
       ('Product3', 834.5, 'описалово'),
       ('Product4', 65.0, ',блаблабла'),
       ('Product5', 834.5, 'описалово'),
       ('Product6', 655.0, ',блаблабла'),
       ('Product7', 2834.5, 'описалово'),
       ('Product8', 635.0, ',блаfghблабла'),
       ('Product9', 8344.5, 'описjалово'),
       ('Product10', 8534.5, 'описалово'),
       ('Product11', 65.60, ',блабhfgалово'),
       ('Product13', 8834.5, 'описаfghлово'),
       ('Product14', 765.0, ',блабhgлабла'),

       ('Product16', 865.0, ',блабfhлабла');



create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price int          not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);


