INSERT INTO tb_teacher (email, graduation, name, registration) VALUES ('email@gmail.com', 'Computação', 'Ramon', '201080904');

INSERT INTO tb_project (description, name, teacher_id) VALUES ('asdasd', 'Projeto 1', 1);

INSERT INTO tb_student (email, name, registration, project_id) VALUES ('email@gmail.com', 'Daniel', '201080303', 1);
INSERT INTO tb_student (email, name, registration, project_id) VALUES ('email2@gmail.com', 'Lucas', '201090803', 1);

INSERT INTO tb_subject (name, room, teacher_id) VALUES ('Web Noturno', '203B', 1);

INSERT INTO tb_subject_has_student (subject_id, student_id) VALUES (1, 1)
INSERT INTO tb_subject_has_student (subject_id, student_id) VALUES (1, 2)

INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Lucas', 'Lucena', 'lucas@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Daniel', 'Xavier', 'daniel@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Ramon', 'Bezerra', 'ramon@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_STUDENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_TEACHER');
INSERT INTO tb_role (authority) VALUES ('ROLE_COORDINATOR');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);