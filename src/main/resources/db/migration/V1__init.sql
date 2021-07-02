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
('Фрукты/овощи'),
('Хлебобулочные изделия'),
('Молочная продукция'),
('Бакалея'),
('Консервы'),
('Соусы');

create table products (
    id              bigserial primary key,
    title           varchar(255),
    price           numeric(8, 2),
    category_id     bigint references categories (id),
    photo           varchar(1024),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into products (title, price, category_id, photo)
values
('Апельсин', 50.25, 1, '//i.ibb.co/fpm7y06/orange.png'),
('Батон', 65.00, 2, '//i.ibb.co/sP34YDY/2.png'),
('Лимон', 36.47, 1, '//i.ibb.co/M2BNvLQ/lemon.jpg'),
('Огурец', 15.84, 1, '//i.ibb.co/nfPNmt0/cucumber.png'),
('Фейхоа', 34.15, 1, '//i.ibb.co/BzSpt0q/feihoa.jpg'),
('Перец жёлтый', 60.00, 1, '//i.ibb.co/MDcXcBY/yellowpepper.webp'),
('Перец зелёный', 60.00, 1, '//i.ibb.co/f8ZGptH/greenpepper.jpg'),
('Перец красный', 60.00, 1, '//i.ibb.co/Yc1Yxw9/redpepper.webp'),
('Йогурт', 56.80, 3, '//i.ibb.co/9HGTdjb/yogurt.png'),
('Банан', 21.54, 1, '//i.ibb.co/6tg7Yph/bananas.png'),
('Мука', 80.00, 4, '//i.ibb.co/rpR6TnB/flour.png'),
('Макароны', 86.00, 4, '//i.ibb.co/M2jt7HM/makaroni.jpg'),
('Тушёнка', 215.50, 5, '//i.ibb.co/Tq8M91d/tushenka.jpg'),
('Майонез', 67.70, 6, '//i.ibb.co/r0ZnDMR/mayonez.jpg'),
('Кетчуп', 114.00, 6, '//i.ibb.co/vkxF0th/ketchup.jpg');

create table orders (
    id                  bigserial primary key,
    user_id             bigint references users (id),
    price               numeric (8, 2),
    address             varchar (255),
    phone               varchar(20),
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

create table feedback (
    id                  bigserial primary key,
    user_id             bigint references users (id),
    product_id          bigint references products (id),
    message             varchar(1024),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);