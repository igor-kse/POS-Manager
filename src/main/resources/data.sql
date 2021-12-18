DROP TABLE IF EXISTS user_comment;
DROP TABLE IF EXISTS request;
DROP TABLE IF EXISTS bank_device;
DROP TABLE IF EXISTS contractor;
DROP TABLE IF EXISTS affiliate;
DROP TABLE IF EXISTS device;
DROP TABLE IF EXISTS firmware;
DROP TABLE IF EXISTS vendor;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS department;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE department
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL
);
CREATE UNIQUE INDEX department_unique_idx ON department(name);

CREATE TABLE users
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    first_name VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL,
    middle_name VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    department_id INTEGER NOT NULL,
    email  VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    registered TIMESTAMP DEFAULT now(),
    enabled BOOLEAN DEFAULT TRUE NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX user_email_idx ON users (email);

CREATE TABLE user_roles
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id INTEGER NOT NULL,
    role VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE vendor
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL,
    CONSTRAINT vendor_name_idx UNIQUE (name)
);

CREATE TABLE firmware
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    vendor_id INTEGER NOT NULL,
    version VARCHAR NOT NULL,
    CONSTRAINT vendor_id_version_idx UNIQUE (vendor_id, version),
    FOREIGN KEY (vendor_id) REFERENCES vendor (id) ON DELETE CASCADE
);

CREATE TABLE device
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    device_type VARCHAR NOT NULL,
    vendor_id INTEGER NOT NULL,
    model VARCHAR NOT NULL,
    FOREIGN KEY (vendor_id) REFERENCES vendor(id) ON DELETE CASCADE
);

CREATE TABLE affiliate
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL,
    CONSTRAINT name_idx UNIQUE (name)
);

CREATE TABLE contractor
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    UNP  VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    address VARCHAR NOT NULL,
    CONSTRAINT unp_idx UNIQUE (UNP)
);

CREATE TABLE bank_device
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    tid VARCHAR(8) NOT NULL,
    contractor_id INTEGER,
    address VARCHAR NOT NULL,
    affiliate_id INTEGER NOT NULL,
    device_id INTEGER NOT NULL,
    firmware_id INTEGER NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (contractor_id) REFERENCES contractor (id) ON DELETE SET NULL,
    FOREIGN KEY (affiliate_id) REFERENCES affiliate (id) ON DELETE CASCADE,
    FOREIGN KEY (device_id) REFERENCES device (id) ON DELETE CASCADE,
    FOREIGN KEY (firmware_id) REFERENCES firmware (id) ON DELETE SET NULL
);
CREATE UNIQUE INDEX bank_device_address_idx ON bank_device (address);

CREATE TABLE request
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title  VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    bank_device_id INTEGER NOT NULL,
    request_type VARCHAR NOT NULL,
    importance_type VARCHAR NOT NULL,
    status_type VARCHAR NOT NULL,
    author_id INTEGER NOT NULL,
    implementor_id INTEGER,
    created TIMESTAMP DEFAULT now(),
    modified TIMESTAMP DEFAULT now(),
    FOREIGN KEY (bank_device_id) REFERENCES bank_device (id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE SET NULL,
    FOREIGN KEY (implementor_id) REFERENCES users (id) ON DELETE SET NULL
);

CREATE TABLE user_comment
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    request_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    content VARCHAR NOT NULL,
    created TIMESTAMP DEFAULT now(),
    modified TIMESTAMP DEFAULT now(),
    FOREIGN KEY (request_id) REFERENCES request (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO department (name)
VALUES ('Департамент 1'),  -- 0
       ('Департамент 2');  -- 1

INSERT INTO users(last_name, first_name, middle_name, city, department_id, email, password)
    VALUES ('Иванов', 'Иван', 'Иванович', 'Минск', 100000, 'ivanov.ivan@gmail.com',
            '$2a$10$KkZsfE4N3TEEcWD3SACHouORHWNGqLjeYPWAyOx/TKXvU3ncPXtj6'),  -- 2
           ('Петров', 'Пётр', 'Петрович', 'Гомель', 100001, 'petrov.petr@gmail.com',
            '$2a$10$S6m4TFFfy9/OEEM8n9/F0eL0LOHf0FE4bX0BwoS.5ZD2oetAj2Z.q'); -- 3

INSERT INTO user_roles(user_id, role)
    VALUES (100003, 'USER'),        -- 4
           (100003, 'IMPLEMENTOR'), -- 5
           (100002, 'ADMIN');       -- 6

INSERT INTO vendor(name)
    VALUES ('Вендор 1'),            -- 7
           ('Вендор 2');            -- 8

INSERT INTO firmware(vendor_id, version)
    VALUES (100007, 'vendor1_version1'),    -- 9
           (100007, 'vendor1_version2'),    -- 10
           (100008, 'vendor2_version1'),    -- 11
           (100008, 'vendor2_version2');    -- 12

INSERT INTO device(device_type, vendor_id, model)
    VALUES ('ATM', 100007, 'Банкомат от Вендор 1'),      -- 13
           ('TERMINAL', 100007, 'Терминал от Вендор 1'), -- 14
           ('ATM', 100008, 'Банкомат> от Вендор 2'),     -- 15
           ('TERMINAL', 100008, 'Терминал от Вендор 2'); -- 16

INSERT INTO affiliate(name)
    VALUES ('Филиал 1'),    -- 17
           ('Филиал 2'),    -- 18
           ('Филиал 3');    -- 19

INSERT INTO contractor(unp, name, city, address)
    VALUES ('УНП1', 'РОГА И КОПЫТА', 'Минск', 'Парнокопытинская, 8'); -- 20

INSERT INTO bank_device(tid, contractor_id, address, affiliate_id, device_id, firmware_id)
    VALUES ('TID_1', 100020, 'Весёлая, 28', 100017, 100013, 100009),    -- 21
           ('TID_2', 100020, 'Невесёлая, 56', 100018, 100014, 100010);  -- 22

INSERT INTO request(title, description, bank_device_id, request_type, importance_type,
                    status_type, author_id, implementor_id)
    VALUES ('Какой-то теоретически очень важный запрос!', 'Я не трогал, я просто смотрел!', 100021, 'CONSULTING',
            'CRITICAL', 'NEW', 100002, 100003), -- 23
           ('И это тоже чей-то запрос!', 'Это не я, оно само!', 100022, 'CONSULTING',
            'IMPORTANT', 'SOLVED', 100002, 100003); -- 24

INSERT INTO user_comment(request_id, user_id, content)
VALUES (100023, 100002, 'Очень важный комментарий!'),        -- 25
       (100023, 100003, 'Шеф! Усё пропало! Усё пропало!'),   -- 26
       (100023, 100003, 'THIS IS SPARTAAAAAAAA'),            -- 27
       (100024, 100002, 'Улетели белые медведи... Почему мне ночью не до сна?'), --28
       (100024, 100003, 'Кролики, собачки и гантели!'),      -- 29
       (100024, 100003, 'Что ж ты со мной делаешь, трава?'); -- 30
