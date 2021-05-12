create table categories (id bigserial primary key, title varchar(255));

insert into categories (title) values ('Продукты питания');

create table products (id bigserial primary key, title varchar(255), price int, category_id bigint references categories (id));

insert into products (title, price, category_id) values
('Апельсин', 50, 1),
('Батон', 25, 1),
('Лимон', 30, 1),
('Огурец', 30, 1),
('Фейхоа', 50, 1),
('Перец жёлтый', 60, 1),
('Перец зелёный', 60, 1),
('Перец красный', 60, 1),
('Йогурт', 56, 1),
('Банан', 21, 1),
('Мука', 80, 1),
('Макароны', 86, 1),
('Тушёнка', 215, 1),
('Майонез', 67, 1),
('Кетчуп', 114, 1),
('Торт', 678, 1),
('Сельдь', 186, 1),
('Масло', 145, 1),
('Ряженка', 67, 1),
('Кинза', 50, 1),
('Мандарин', 89, 1),
('Лук', 30, 1),
('Свекла', 25, 1),
('Творог', 134, 1),
('Молоко', 70, 1);