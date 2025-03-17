INSERT INTO poc_authentication_db.roles
	(name, description, created_at)
VALUES
    ('ADMIN', 'Administrator role', '2024-08-05 17:06:18.179'),
    ('USER', 'Default user role', '2024-08-05 17:06:18.179');
    
INSERT INTO poc_authentication_db.users
	(username, password, created_at, updated_at, person_id)
VALUES
	('eduardo.marques', '12345678', '2024-08-05 17:06:18.179', '2024-08-05 17:06:18.179', 1),
	('carlos.campos', '12345678', '2024-08-05 17:06:18.179', '2024-08-05 17:06:18.179', 2);
	
INSERT INTO poc_authentication_db.rel_user_roles
	(user_id, role_id)
VALUES
	(1, 1),
	(1, 2),
	(2, 2);