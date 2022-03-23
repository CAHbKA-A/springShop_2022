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
values ('Product1', 1.76, 'описалово'),
       ('Product2', 10.54, 'blahblahblah'),
       ('Product3', 834.5, 'описалово'),
       ('Product4', 65.0, ',блаблабла');




