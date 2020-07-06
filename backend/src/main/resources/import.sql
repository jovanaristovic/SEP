INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'AUTHOR_ROLE');



INSERT INTO membership (id, date_from, date_to, is_active ) values (1, '2019-01-10 14:45', '2020-01-10 14:45', false);

INSERT INTO users (name, last_name, email,password, enabled, username) VALUES ('Milica','Matic','user@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user@yahoo.com' );

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);

INSERT INTO purchase(product_id, status, type_of_product) values (2,"New", "Magazine");
INSERT INTO purchase(product_id, status, type_of_product) values (1,"New", "Work");
INSERT INTO purchase(product_id, status, type_of_product) values (3,"Paid", "Magazine");


INSERT INTO users_purchases(users_id, purchases_id) values (1,1);
INSERT INTO users_purchases(users_id, purchases_id) values (1,2);
INSERT INTO users_purchases(users_id, purchases_id) values (1,3);

INSERT INTO scientificfields(id, name) values (1,'geografija');
INSERT INTO scientificfields(id, name) values (2,'fizika');
INSERT INTO scientificfields(id, name) values (3,'hemija');
INSERT INTO scientificfields(id, name) values (4,'zoologija');


INSERT INTO journal (issn, is_open_access, title, is_active, price, scientific_field_id) values ('nd89', false, 'Nacionalna geografija', true, 34,1);
INSERT INTO journal (issn, is_open_access, title, is_active, price, scientific_field_id) values ('123kdk', true , 'Cosmopolitan', true, 93,2);
INSERT INTO journal (issn, is_open_access, title, is_active, price, scientific_field_id) values ('123kdk', false , 'Istorija', true, 93,2);


INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('df','gf',1, 23, 'Kontrolna-tacka-1.pdf' );
INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('dfhj','gukf',1, 23, 'Kontrolna-tacka-2.pdf');


INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('dfj','gjkf',1, 11, 'Kontrolna-tacka-1.pdf' );
INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('dfjhj','gukjkf',1, 32, 'Kontrolna-tacka-2.pdf');


INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('dfj','gjkf',1, 11, 'Kontrolna-tacka-1.pdf' );
INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('dfjhj','gukjkf',1, 32, 'Kontrolna-tacka-2.pdf');


INSERT INTO journal_works (journal_id, works_id) values (1,1);
INSERT INTO journal_works (journal_id, works_id) values (1,2);
INSERT INTO journal_works (journal_id, works_id) values (2,3);
INSERT INTO journal_works (journal_id, works_id) values (2,4);
INSERT INTO journal_works (journal_id, works_id) values (3,5);
INSERT INTO journal_works (journal_id, works_id) values (3,6);


