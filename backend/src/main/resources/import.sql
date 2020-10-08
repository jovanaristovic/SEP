INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'AUTHOR_ROLE');



INSERT INTO membership (id, date_from, date_to, is_active ) values (1, '2019-01-10 14:45', '2020-01-10 14:45', false);

INSERT INTO users (name, last_name, email,password, enabled, username) VALUES ('Milica','Matic','user@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user@yahoo.com' );

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);

-- INSERT INTO purchase(product_id, status, type_of_product) values (2,"New", "Magazine");
-- INSERT INTO purchase(product_id, status, type_of_product) values (1,"New", "Work");
-- INSERT INTO purchase(product_id, status, type_of_product) values (3,"New", "Magazine");
--
--
-- INSERT INTO users_purchases(users_id, purchases_id) values (1,1);
-- INSERT INTO users_purchases(users_id, purchases_id) values (1,2);
-- INSERT INTO users_purchases(users_id, purchases_id) values (1,3);

INSERT INTO scientificfields(id, name) values (1,'geografija');
INSERT INTO scientificfields(id, name) values (2,'fizika');
INSERT INTO scientificfields(id, name) values (3,'hemija');
INSERT INTO scientificfields(id, name) values (4,'zoologija');
INSERT INTO scientificfields(id, name) values (5,'istorija');



INSERT INTO journal (issn, is_open_access, title, is_active, price, scientific_field_id) values ('nd89', false, 'Nacionalna geografija', true, 8,1);
INSERT INTO journal (issn, is_open_access, title, is_active, price, scientific_field_id) values ('123kdk', true , 'Fizika', true, 6,2);
INSERT INTO journal (issn, is_open_access, title, is_active, price, scientific_field_id) values ('9393', false , 'Istorija', true,4,5);


INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('apstrakt1','Title1',1, 23, '10_najvecis_sisara.pdf' );
INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('apstrakt2','Title2',1, 23, 'kongres_2009.pdf');


INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('apstrakt3','Title3',1, 11, '10_najvecis_sisara.pdf' );
INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('apstrakt4','Title4',1, 32, 'kongres_2009.pdf');


INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('apstrakt5','Title5',1, 11, '10_najvecis_sisara.pdf' );
INSERT INTO work(apstrakt, title, scientific_field_id, price, file_name) values ('apstrakt6','Title6',1, 32, 'kongres_2009.pdf');


INSERT INTO journal_works (journal_id, works_id) values (1,1);
INSERT INTO journal_works (journal_id, works_id) values (1,2);
INSERT INTO journal_works (journal_id, works_id) values (2,3);
INSERT INTO journal_works (journal_id, works_id) values (2,4);
INSERT INTO journal_works (journal_id, works_id) values (3,5);
INSERT INTO journal_works (journal_id, works_id) values (3,6);


