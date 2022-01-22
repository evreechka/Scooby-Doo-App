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

INSERT INTO CHARACTER(name, surname, age, sex)
VALUES ('Fred', 'Jones', 15, 'MALE'),
       ('Daphne', 'Black', 15, 'FEMALE'),
       ('Shaggy', 'Rogers', 15, 'MALE'),
       ('Velma', 'Dinkley', 15, 'FEMALE'),
       ('Scoobert', 'Doo', 5, 'MALE'),
       ('Jonson', 'Stone', 60, 'MALE'),
       ('Kate', 'Hubbert', 30, 'FEMALE');

INSERT INTO INVESTIGATOR (investigator_id, feature)
VALUES (1, 'FEARLESS'),
       (2, 'INTUITION'),
       (3, 'SPEED'),
       (4, 'INTELLECT'),
       (5, 'SPEED');

INSERT INTO PROFILE (username, password, profile_photo, system_role, user_id)
VALUES ('bestCatcher', '$2a$10$9e1psHZNgq0sw8qFmPHsi.sLpB7XTrqI9gcG8SgkhGTP8R.8.B9Fe', null, 'INVESTIGATOR', 1),
       ('beautyGirl2002', '$2a$10$k.QXoepc0C8nAJPDLgqgG.FT7nlbdd984H/XsY6FK7T1xT1.wjYGi', null, 'INVESTIGATOR', 2),
       ('foodLover', '$2a$10$mx.HBoNtOgqN24KeID/x8.EgCq4NwXtD0AWMPMAEtjJO7RBlK5Ii.', null, 'INVESTIGATOR', 3),
       ('velma brain', '$2a$10$QzlHzMfCKkNOB0zzioNRS.nO3syzIEKYPKIwH6lSg2jbMG06ESA4.', null, 'ADMIN', 4),
       ('scoobs', '$2a$10$MIEH.rwf6vGw5o08z66p7Olwt3L6TwvFZLMxfVomf8Tbi7gw8OgMK', null, 'INVESTIGATOR', 5);
INSERT INTO BANK_ACCOUNT (balance, owner_id)
VALUES (1000.0, 1),
       (1000.0, 2),
       (1000.0, 3),
       (1000.0, 4),
       (1000.0, 5);
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