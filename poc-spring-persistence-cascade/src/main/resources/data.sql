INSERT INTO DEV_POC_SPRING_CASCADE_DATABASE.POC_SPRING_CASCADE_PERSON
    (NAME_PERSON, AGE_PERSON, HEIGHT_PERSON, WEIGHT_PERSON)
VALUES
    ('Tony Stark', 44, '1,78m', '78kg'),
    ('Dr. Estranho', 39, '1,81m', '69kg'),
    ('Hulk/Bruce Baner', 41, '3,24m/1,88m', '318kg/71kg'),
    ('Capt. Rogers', 37, '1,88', '81kg');

INSERT INTO DEV_POC_SPRING_CASCADE_DATABASE.POC_SPRING_CASCADE_CONTACT
    (ID_TYPE_CONTACT, CONTACT_CONTACT)
VALUES
    (1, '(657)208-3903'),
    (2, '(651)326-9980'),
    (1, '(331)330-4261'),
    (1, '(305)513-2200'),
    (2, '(475)243-6635');
    
INSERT into DEV_POC_SPRING_CASCADE_DATABASE.POC_SPRING_CASCADE_REL_PERSON_CONTACT
    (ID_PERSON, ID_CONTACT)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3),
    (4, 4),
    (4, 5);
    
INSERT INTO DEV_POC_SPRING_CASCADE_DATABASE.POC_SPRING_CASCADE_SYSTEM_VALUE
    (ID_SYSTEM_VALUE_TYPE, DESCRIPTION_SYSTEM_VALUE, CODE_SYSTEM_VALUE)
VALUES
    (1, 'CELLPHONE', 'Cellphone'),
    (1, 'TELLPHONE', 'Tellphone');
    
INSERT INTO DEV_POC_SPRING_CASCADE_DATABASE.POC_SPRING_CASCADE_SYSTEM_VALUE_TYPE
    (DESCRIPTION_SYSTEM_VALUE_TYPE, CODE_SYSTEM_VALUE_TYPE)
VALUES
    ('CONTACT_TYPE', 'Contact Type'),
    ('DOCUMENT_TYPE', 'Document Type');
    
INSERT INTO DEV_POC_SPRING_CASCADE_DATABASE.POC_SPRING_CASCADE_USER
    (ID_PERSON, LOGIN_USER, PASSWORD_USER)
VALUES
    (1, 'tony.stark', '12345678'),
    (2, 'steve.strange', '12345678'),
    (3, 'bruce.banner', '12345678'),
    (4, 'steve.rogers', '12345678');