INSERT INTO DEV_POC_SPRING_CASCADE_DATABASE.POC_SPRING_CASCADE_PERSON
    (NAME_PERSON, AGE_PERSON, HEIGHT_PERSON, WEIGHT_PERSON)
VALUES
    ('Tony Stark', 44, '1,78m', '78kg'),
    ('Dr. Estranho', 39, '1,81m', '69kg'),
    ('Hulk/Bruce Baner', 41, '3,24m/1,88m', '318kg/71kg'),
    ('Capt. Rogers', 37, '1,88', '81kg');

INSERT INTO DEV_POC_SPRING_CASCADE_DATABASE.POC_SPRING_CASCADE_CONTACT
    (TYPE_CONTACT, CONTACT_CONTACT)
VALUES
    ('Cellphone', '(657)208-3903'),
    ('Tellphone', '(651)326-9980'),
    ('Cellphone', '(331)330-4261'),
    ('Cellphone', '(305)513-2200'),
    ('Tellphone', '(475)243-6635');
    
INSERT INTO DEV_POC_SPRING_CASCADE_DATABASE.POC_SPRING_CASCADE_REL_PERSON_CONTACT
    (ID_PERSON, ID_CONTACT)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3),
    (4, 4),
    (4, 5);