INSERT INTO MONSTER_TYPE (name, monster_feature)
VALUES ('Witch', 'WITCHCRAFT'),
       ('Zombie', 'NONE'),
       ('Fantom', 'FLYING'),
       ('Vampire', 'CUTTER'),
       ('Ghost', 'FLYING'),
       ('Goblin', 'POWERFUL'),
       ('Aliens', 'POWERFUL'),
       ('Fish', 'WATERING'),
       ('Fairy', 'FLYING'),
       ('Pirate', 'CUTTER'),
       ('Toxic', 'EXPLOSIVE'),
       ('Dinosaur', 'CUTTER'),
       ('Alligator', 'WATERING'),
       ('FireBender', 'EXPLOSIVE'),
       ('Werewolf', 'CUTTER'),
       ('Beast', 'CUTTER'),
       ('Bird', 'FLYING'),
       ('Skeleton', 'NONE'),
       ('Bully', 'POWERFUL');

SELECT generate_monster()
FROM generate_series(1, 10000);

INSERT INTO CHARACTER(name, surname, age, sex, system_role)
VALUES ('Fred', 'Jones', 15, 'MALE', 'INVESTIGATOR'),
       ('Daphne', 'Black', 15, 'FEMALE', 'INVESTIGATOR'),
       ('Shaggy', 'Rogers', 15, 'MALE', 'INVESTIGATOR'),
       ('Velma', 'Dinkley', 15, 'FEMALE', 'ADMIN'),
       ('Scoobert', 'Doo', 5, 'MALE', 'INVESTIGATOR');
SELECT generate_character()
FROM generate_series(1, 20000);

SELECT generate_address()
FROM generate_series(1, 50000);

SELECT generate_living_place()
FROM generate_series(1, 100000);

SELECT generate_crime_scene()
FROM generate_series(1, 6000);

SELECT generate_crime()
FROM generate_series(1, 5000);

SELECT generate_crime_visit()
FROM generate_series(1, 8000);

SELECT generate_victim()
FROM generate_series(1, 10000);

SELECT generate_suspect()
FROM generate_series(1, 9000);

SELECT generate_criminal_case()
FROM generate_series(1, 10000);

SELECT generate_clue()
FROM generate_series(1, 20000);

SELECT generate_suspect_clue()
FROM generate_series(1, 20000);

INSERT INTO INVESTIGATOR (investigator_id, feature)
VALUES (1, 'FEARLESS'),
       (2, 'INTUITION'),
       (3, 'SPEED'),
       (4, 'INTELLECT'),
       (5, 'SPEED');

INSERT INTO PROFILE (username, password, profile_photo, user_id)
VALUES ('bestCatcher', '$2a$10$9e1psHZNgq0sw8qFmPHsi.sLpB7XTrqI9gcG8SgkhGTP8R.8.B9Fe', null, 1),
       ('beautyGirl2002', '$2a$10$k.QXoepc0C8nAJPDLgqgG.FT7nlbdd984H/XsY6FK7T1xT1.wjYGi', null, 2),
       ('foodLover', '$2a$10$mx.HBoNtOgqN24KeID/x8.EgCq4NwXtD0AWMPMAEtjJO7RBlK5Ii.', null, 3),
       ('velma brain', '$2a$10$QzlHzMfCKkNOB0zzioNRS.nO3syzIEKYPKIwH6lSg2jbMG06ESA4.', null, 4),
       ('scoobs', '$2a$10$MIEH.rwf6vGw5o08z66p7Olwt3L6TwvFZLMxfVomf8Tbi7gw8OgMK', null, 5);

INSERT INTO BANK_ACCOUNT (balance, owner_id)
VALUES (1000.0, 1),
       (1000.0, 2),
       (1000.0, 3),
       (1000.0, 4),
       (1000.0, 5);

SELECT generate_visit_participant()
FROM generate_series(1, 8000);

SELECT generate_case_order()
FROM generate_series(1, 5000);

INSERT INTO ITEM (name, type, cost)
VALUES ('Rope', 'TRAP', 10.0),
       ('Net', 'TRAP', 50.0),
       ('Canvas', 'TRAP', 3.0),
       ('Cell', 'TRAP', 100.0),
       ('Ship', 'TRAP', 3000.0),
       ('Chain', 'TRAP', 50.0),
       ('Lamp', 'TRAP', 30.0),
       ('Hammer', 'TRAP', 5.0),
       ('Air Plane', 'TRAP', 5000.0),
       ('Underwater suit', 'EQUIPMENT', 1500.0),
       ('Underground suit', 'EQUIPMENT', 1500.0),
       ('Basic suit', 'EQUIPMENT', 1500.0),
       ('Space Suit', 'EQUIPMENT', 2000.0);

SELECT generate_item_cart()
FROM generate_series(1, 5000);

SELECT generate_inventory()
FROM generate_series(1, 500);

SELECT generate_history()
FROM generate_series(1, 300);

SELECT generate_trap_case()
FROM generate_series(1, 10000);

SELECT generate_trap_item()
FROM generate_series(1, 10000);

SELECT generate_item_monster()
From generate_series(1, 200);

SELECT generate_equipment_case()
FROM generate_series(1, 10000);

SELECT generate_contention()
FROM generate_series(1, 5000);

SELECT generate_investigator_crime()
FROM generate_series(1, 5000);