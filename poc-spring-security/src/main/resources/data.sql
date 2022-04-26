INSERT INTO DEV_POC_SPRING_SECURITY_DATABASE.POC_SPRING_SECURITY_USER
    (USERNAME_USER, PASSWORD_USER)
VALUES
    ('tony.stark', '12345678'),
    ('steve.strange', '12345678'),
    ('bruce.banner', '12345678'),
    ('steve.rogers', '12345678');
    
INSERT INTO DEV_POC_SPRING_SECURITY_DATABASE.POC_SPRING_SECURITY_ROLE
    (ID_ROLE, CODE_ROLE, DESCRIPTION_ROLE, NAME_ROLE)
VALUES
    (1, 'ROLE_ADMIN', 'System Administrator', 'System Admin'),
    (2, 'USER', 'System User', 'System user');
   
INSERT into DEV_POC_SPRING_SECURITY_DATABASE.POC_SPRING_SECURITY_REL_USER_ROLE
    (ID_USER, ID_ROLE)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 1),
    (3, 2),
    (4, 2);

    
