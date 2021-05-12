create table categories (
    id              bigserial primary key,
    title           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title)
values ('Продукты питания');

create table products (
    id              bigserial primary key,
    title           varchar(255),
    price           numeric(8, 2),
    category_id     bigint references categories (id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into products (title, price, category_id)
values
('Апельсин', 50.25, 1),
('Батон', 65.00, 1),
('Лимон', 36.47, 1),
('Огурец', 15.84, 1),
('Фейхоа', 34.15, 1),
('Перец жёлтый', 60.00, 1),
('Перец зелёный', 60.00, 1),
('Перец красный', 60.00, 1),
('Йогурт', 56.80, 1),
('Банан', 21.54, 1),
('Мука', 80.00, 1),
('Макароны', 86.00, 1),
('Тушёнка', 215.50, 1),
('Майонез', 67.70, 1),
('Кетчуп', 114.00, 1),
('Торт', 678.89, 1),
('Сельдь', 186.00, 1),
('Масло', 145.00, 1),
('Ряженка', 67.00, 1),
('Кинза', 54.45, 1),
('Мандарины', 89.90, 1),
('Лук', 17.87, 1),
('Свёкла', 13.07, 1),
('Творог', 134.00, 1),
('Молоко', 70.00, 1);

create table order_items (
    id                  bigserial primary key,
    product_id          bigint references products (id),
    quantity            int,
    price_per_product   numeric (8, 2),
    price               numeric (8, 2),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);