create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    description varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);


-- продукты
insert into products (title, price, description)
values ('Product1', 34, 'описалово'),
       ('Product2', 8340, 'blahblahblah'),
       ('Product3', 8345, 'описалово'),
       ('Product4', 650, ',блаблабла');

