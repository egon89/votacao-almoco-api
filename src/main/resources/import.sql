-- Usuários
insert into tb_usuario (id, login) values (1, 'admin');
insert into tb_usuario (id, login) values (2, 'apurador');
insert into tb_usuario (id, login) values (3, 'faminto_1');
insert into tb_usuario (id, login) values (4, 'faminto_2');
insert into tb_usuario (id, login) values (5, 'faminto_3');
insert into tb_usuario (id, login) values (4, 'faminto_4');
insert into tb_usuario (id, login) values (5, 'faminto_5');
insert into tb_usuario (id, login) values (6, 'faminto_6');
insert into tb_usuario (id, login) values (7, 'faminto_7');
insert into tb_usuario (id, login) values (8, 'faminto_8');
insert into tb_usuario (id, login) values (9, 'faminto_9');
insert into tb_usuario (id, login) values (10, 'faminto_10');


-- Restaurantes
insert into tb_restaurante (id, nome) values (1, 'Restaurante 1');
insert into tb_restaurante (id, nome) values (2, 'Restaurante 2');
insert into tb_restaurante (id, nome) values (3, 'Restaurante 3');
insert into tb_restaurante (id, nome) values (4, 'Restaurante 4');
insert into tb_restaurante (id, nome) values (5, 'Restaurante 5');
insert into tb_restaurante (id, nome) values (6, 'Restaurante 6');
insert into tb_restaurante (id, nome) values (7, 'Restaurante 7');
insert into tb_restaurante (id, nome) values (8, 'Restaurante 8');
insert into tb_restaurante (id, nome) values (9, 'Restaurante 9');

-- Eleições
insert into tb_eleicao (id, inclusao, id_restaurante, votos) values (1, '2020-03-16', 7, 2);
insert into tb_eleicao (id, inclusao, id_restaurante, votos) values (2, '2020-03-17', 8, 2);
insert into tb_eleicao (id, inclusao, id_restaurante, votos) values (3, '2020-03-18', 9, 2);