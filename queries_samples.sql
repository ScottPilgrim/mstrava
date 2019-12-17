INSERT INTO `users` VALUES (1, 'ivan', SHA2("password", 512), 'Ivan', 'Ustinov');
INSERT INTO `runs` VALUES (1, 1, 5430, 330, '1000-01-01 00:00:00', '9999-12-31 23:59:59');

INSERT INTO runs VALUES (default, (SELECT user_id FROM users WHERE login="ivan"), 8430, 530, '2019-12-31 15:00:00', '2019-12-31 15:49:59');