DROP DATABASE IF EXISTS mstrava;
CREATE DATABASE mstrava;
USE mstrava;

CREATE TABLE `users` (
	`user_id` integer NOT NULL auto_increment PRIMARY KEY,
    `login` varchar(50) NOT NULL,
    `password` binary(128) NOT NULL,
    `first_name` varchar(50),
    `last_name` varchar(50)
);

CREATE TABLE `runs` (
	`run_id` integer NOT NULL auto_increment PRIMARY KEY,
    `user_id` integer NOT NULL,
	`distance` integer NOT NULL,
    `calories` integer NOT NULL,
    `start` datetime NOT NULL,
    `end` datetime NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES users(`user_id`)
);

INSERT INTO `users` VALUES (default, 'ivan', SHA2("password", 512), 'Ivan', 'Ustinov');
INSERT INTO runs VALUES (default, (SELECT user_id FROM users WHERE login="ivan"), 8430, 530, '2019-12-31 15:00:00', '2019-12-31 15:49:59');