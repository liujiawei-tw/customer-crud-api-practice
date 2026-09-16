create table customers (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) not null,
    email varchar(255) unique not null,
    phone varchar(30),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);