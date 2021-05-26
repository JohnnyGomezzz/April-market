create table users (
    id              bigserial primary key,
    username        varchar(30) not null unique,
    password        varchar(80) not null,
    email           varchar(80) unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table roles (
    id              bigserial primary key,
    name            varchar(50) not null unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id         bigint not null references users (id),
    role_id         bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('user', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'bob_johnson@gmail.com'),
('admin', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

create table categories (
    id              bigserial primary key,
    title           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title)
values
('Продукты питания');

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

create table orders (
    id                  bigserial primary key,
    user_id             bigint references users (id),
    price               numeric (8, 2),
    address             varchar (255),
    phone               bigint,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table order_items (
    id                  bigserial primary key,
    order_id            bigint references orders (id),
    product_id          bigint references products (id),
    quantity            int,
    price_per_product   numeric (8, 2),
    price               numeric (8, 2),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);