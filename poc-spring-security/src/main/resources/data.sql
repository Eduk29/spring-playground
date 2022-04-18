INSERT INTO DEV_POC_SPRING_SECURITY_DATABASE.POC_SPRING_SECURITY_USER
    (ID_PERSON, LOGIN_USER, PASSWORD_USER)
VALUES
    (1, 'tony.stark', '12345678'),
    (2, 'steve.strange', '12345678'),
    (3, 'bruce.banner', '12345678'),
    (4, 'steve.rogers', '12345678');
    
INSERT INTO DEV_POC_SPRING_SECURITY_DATABASE.POC_SPRING_SECURITY_ROLE
    (ID_ROLE, CODE_ROLE, DESCRIPTION_ROLE, NAME_ROLE)
VALUES
    (1, 'SYS_ADMIN', 'System Administrator', 'System Admin'),
    (2, 'SYS_USER', 'System User', 'System user');
   
INSERT into DEV_POC_SPRING_SECURITY_DATABASE.POC_SPRING_SECURITY_REL_USER_ROLE
    (ID_USER, ID_ROLE)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 1),
    (3, 2),
    (4, 2);
   
INSERT INTO DEV_POC_SPRING_SECURITY_DATABASE.POC_SPRING_SECURITY_PERSON
    (ID_PERSON, NAME_PERSON)
VALUES
    (1, 'Tony Stark'),
    (2, 'Steve Strange'),
    (3, 'Bruce Banner'),
    (4, 'Steve Rogers');
    
