INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'AUTHOR_ROLE');



INSERT INTO membership (id, date_from, date_to, is_active ) values (1, '2019-01-10 14:45', '2020-01-10 14:45', false);

INSERT INTO users (name, last_name, email,password, enabled, username) VALUES ('Milica','Matic','user@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user@yahoo.com' );

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);



INSERT INTO scientificfields(id, name) values (1,'geografija');
INSERT INTO scientificfields(id, name) values (2,'fizika');
INSERT INTO scientificfields(id, name) values (3,'hemija');

INSERT INTO journal (issn, is_open_access, title, is_active, price, scientific_field_id) values ('nd89', true, 'Nacionalna geografija', true, 34,1);
INSERT INTO journal (issn, is_open_access, title, is_active, price, scientific_field_id) values ('123kdk', false, 'Cosmopolitan', true, 93,2);


INSERT INTO work(apstrakt, title, scientific_field_id, price) values ('df','gf',1, 23 );

INSERT INTO journal_works (journal_id, works_id) values (1,1);