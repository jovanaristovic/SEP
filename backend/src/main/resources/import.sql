INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');


INSERT INTO membership (id, date_from, date_to, is_active ) values (1, '2019-01-10 14:45', '2020-01-10 14:45', false);

INSERT INTO users (reviewer, name, last_name, email,password, enabled, username) VALUES (false, 'Milica','Matic','user@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user@yahoo.com' );

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
