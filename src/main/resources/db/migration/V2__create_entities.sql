CREATE TYPE monster_feature_type as enum ('WATERING','POWERFUL','CUTTER','FLYING','EXPLOSIVE', 'NONE', 'WITCHCRAFT');
CREATE type sex_type as enum ('FEMALE','MALE','OTHER');
CREATE type system_role_type as enum ('USER','SHERIFF','ADMIN','INVESTIGATOR');
CREATE type place_type as enum ('PUBLIC','PRIVATE_HOUSE','COMPANY_BUILDING','STREET');
CREATE type feature_type as enum ('INTELLECT','INTUITION','SPEED','FEARLESS');
CREATE type visit_role_type as enum ('CLUE_SEARCHER','VICTIM_INTERVIEW','CRIME_SCENE_INSPECTOR','BAIT','BOBBY_TRAPS','TRAP_MANAGER');
CREATE type order_status_type as enum ('NOT_CONFIRMED','ON_WAY','READY');
CREATE type item_type as enum ('TRAP','EQUIPMENT');
CREATE type punishment_type as enum ('NONE','PUBLIC_WORKS','FINE','ARREST');
CREATE type crime_status_type as enum ('ACTIVE','POSTPON','CLOSED');

CREATE TABLE IF NOT EXISTS MONSTER_TYPE
(
    monster_type_id BIGSERIAL PRIMARY KEY,
    name            TEXT NOT NULL,
    monster_feature monster_feature_type
);

CREATE TABLE IF NOT EXISTS MONSTER
(
    monster_id      BIGSERIAL PRIMARY KEY,
    name            TEXT NOT NULL,
    description     TEXT,
    weight          DOUBLE PRECISION CHECK (weight > 0.0),
    height          DOUBLE PRECISION CHECK (height > 0.0),
    internet_inform TEXT,
    type_id         BIGINT,
    FOREIGN KEY (type_id) REFERENCES MONSTER_TYPE (monster_type_id)
);

CREATE TABLE IF NOT EXISTS CHARACTER
(
    character_id BIGSERIAL PRIMARY KEY,
    name         TEXT             NOT NULL,
    surname      TEXT             NOT NULL,
    age          INT CHECK (age > 0 AND age < 120),
    sex          sex_type,
    system_role  system_role_type NOT NULL
);

CREATE TABLE IF NOT EXISTS ADDRESS
(
    address_id BIGSERIAL PRIMARY KEY,
    city       TEXT NOT NULL,
    avenue     TEXT NOT NULL,
    house_num  BIGINT CHECK (house_num >= 1)
);

CREATE TABLE IF NOT EXISTS LIVING_PLACE
(
    address_id   BIGINT,
    character_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES ADDRESS (address_id),
    FOREIGN KEY (character_id) REFERENCES CHARACTER (character_id)
);

CREATE TABLE IF NOT EXISTS CRIME_SCENE
(
    scene_id   BIGSERIAL PRIMARY KEY,
    name       TEXT NOT NULL,
    place      place_type,
    address_id BIGINT UNIQUE,
    FOREIGN KEY (address_id) REFERENCES ADDRESS (address_id)
);

CREATE TABLE IF NOT EXISTS CRIME
(
    crime_id     BIGSERIAL PRIMARY KEY,
    crime_status crime_status_type NOT NULL,
    description  TEXT,
    fee          DOUBLE PRECISION CHECK ( fee > 0.0 ),
    sheriff_id   BIGINT,
    FOREIGN KEY (sheriff_id) REFERENCES CHARACTER (character_id)
);

CREATE TABLE IF NOT EXISTS CRIME_VISIT
(
    visit_id             BIGSERIAL PRIMARY KEY,
    visit_num            INT CHECK ( visit_num > 0 ),
    severity_destruction INT CHECK (severity_destruction >= 0 AND severity_destruction <= 10),
    date_visit           TIMESTAMP NOT NULL,
    crime_id             BIGINT,
    scene_id             BIGINT,
    FOREIGN KEY (scene_id) REFERENCES CRIME_SCENE (scene_id),
    FOREIGN KEY (crime_id) REFERENCES CRIME (crime_id)
);

CREATE TABLE IF NOT EXISTS VICTIM
(
    indication      TEXT      NOT NULL,
    date_indication TIMESTAMP NOT NULL,
    visit_id        BIGINT,
    character_id    BIGINT,
    FOREIGN KEY (visit_id) REFERENCES CRIME_VISIT (visit_id),
    FOREIGN KEY (character_id) REFERENCES CHARACTER (character_id)
);

CREATE TABLE IF NOT EXISTS SUSPECT
(
    suspect_id   BIGSERIAL PRIMARY KEY,
    motive       TEXT NOT NULL,
    involvement  INT CHECK (involvement > 0),
    visit_id     BIGINT,
    character_id BIGINT,
    FOREIGN KEY (visit_id) REFERENCES CRIME_VISIT (visit_id),
    FOREIGN KEY (character_id) REFERENCES CHARACTER (character_id)
);

CREATE TABLE IF NOT EXISTS CRIMINAL_CASE
(
    case_id       BIGSERIAL PRIMARY KEY,
    punishment    punishment_type,
    severity_case INT CHECK ( severity_case >= 0 AND severity_case <= 10 ),
    monster_id    BIGINT UNIQUE,
    quilt_id      BIGINT,
    crime_id      BIGINT,
    FOREIGN KEY (monster_id) REFERENCES MONSTER (monster_id),
    FOREIGN KEY (quilt_id) REFERENCES SUSPECT (suspect_id),
    FOREIGN KEY (crime_id) REFERENCES CRIME (crime_id)
);

CREATE TABLE IF NOT EXISTS CLUE
(
    clue_id     BIGSERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT NOT NULL,
    visit_id    BIGINT,
    case_id     BIGINT,
    FOREIGN KEY (visit_id) REFERENCES CRIME_VISIT (visit_id),
    FOREIGN KEY (case_id) REFERENCES CRIMINAL_CASE (case_id)
);

CREATE TABLE IF NOT EXISTS SUSPECT_CLUE
(
    clue_id    BIGINT,
    suspect_id BIGINT,
    FOREIGN KEY (clue_id) REFERENCES CLUE (clue_id),
    FOREIGN KEY (suspect_id) REFERENCES SUSPECT (suspect_id)
);

CREATE TABLE IF NOT EXISTS INVESTIGATOR
(
    investigator_id BIGINT PRIMARY KEY,
    feature         feature_type,
    FOREIGN KEY (investigator_id) REFERENCES CHARACTER (character_id)
);

CREATE TABLE IF NOT EXISTS PROFILE
(
    profile_id    BIGSERIAL PRIMARY KEY,
    username      TEXT NOT NULL,
    password      TEXT NOT NULL,
    profile_photo BYTEA,
    user_id       BIGINT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES INVESTIGATOR (investigator_id)
);

CREATE TABLE IF NOT EXISTS BANK_ACCOUNT
(
    bank_account_id BIGSERIAL PRIMARY KEY,
    balance         DOUBLE PRECISION CHECK (balance > 0.0),
    owner_id        BIGINT UNIQUE,
    FOREIGN KEY (owner_id) REFERENCES INVESTIGATOR (investigator_id)
);

CREATE TABLE IF NOT EXISTS VISIT_PARTICIPANT
(
    visit_role     visit_role_type,
    visit_id       BIGINT,
    participant_id BIGINT,
    FOREIGN KEY (participant_id) REFERENCES INVESTIGATOR (investigator_id),
    FOREIGN KEY (visit_id) REFERENCES CRIME_VISIT (visit_id)
);

CREATE TABLE IF NOT EXISTS CASE_ORDER
(
    order_id     BIGSERIAL PRIMARY KEY,
    date_order   TIMESTAMP NOT NULL,
    date_deliver TIMESTAMP NOT NULL,
    order_status order_status_type,
    total_cost   DOUBLE PRECISION CHECK (total_cost > 0.0),
    case_id      BIGINT,
    orderer_id   BIGINT,
    FOREIGN KEY (orderer_id) REFERENCES INVESTIGATOR (investigator_id),
    FOREIGN KEY (case_id) REFERENCES CRIMINAL_CASE (case_id)
);

CREATE TABLE IF NOT EXISTS ITEM
(
    item_id BIGSERIAL PRIMARY KEY,
    name    TEXT NOT NULL,
    type    item_type,
    cost    DOUBLE PRECISION CHECK ( cost > 0.0 )
);

CREATE TABLE IF NOT EXISTS ITEM_CART
(
    total_count  INT CHECK ( total_count > 0 ),
    date_receipt TIMESTAMP NOT NULL,
    item_id      BIGINT,
    order_id     BIGINT,
    FOREIGN KEY (item_id) REFERENCES ITEM (item_id),
    FOREIGN KEY (order_id) REFERENCES CASE_ORDER (order_id)
);

CREATE TABLE IF NOT EXISTS INVENTORY
(
    item_serial_num BIGSERIAL PRIMARY KEY,
    is_available    bool NOT NULL,
    deposit_date    TIMESTAMP,
    item_id         BIGINT,
    FOREIGN KEY (item_id) REFERENCES ITEM (item_id)
);

CREATE TABLE IF NOT EXISTS HISTORY
(
    time_take       TIMESTAMP,
    time_return     TIMESTAMP,
    item_serial_num BIGINT,
    investigator_id BIGINT,
    FOREIGN KEY (item_serial_num) REFERENCES INVENTORY (item_serial_num),
    FOREIGN KEY (investigator_id) REFERENCES INVESTIGATOR (investigator_id)
);

CREATE TABLE IF NOT EXISTS TRAP_CASE
(
    trap_case_id BIGSERIAL PRIMARY KEY,
    name         TEXT,
    usefulness   INT CHECK ( usefulness >= 0 AND usefulness <= 10),
    is_selected  bool,
    case_id      BIGINT,
    FOREIGN KEY (case_id) REFERENCES CRIMINAL_CASE (case_id)
);

CREATE TABLE IF NOT EXISTS TRAP_ITEM
(
    necessary_count INT CHECK ( necessary_count > 0),
    item_id         BIGINT,
    trap_id         BIGINT,
    FOREIGN KEY (item_id) REFERENCES ITEM (item_id),
    FOREIGN KEY (trap_id) REFERENCES TRAP_CASE (trap_case_id)
);

CREATE TABLE IF NOT EXISTS ITEM_MONSTER
(
    item_id         BIGINT,
    monster_type_id BIGINT,
    FOREIGN KEY (item_id) REFERENCES ITEM (item_id),
    FOREIGN KEY (monster_type_id) REFERENCES MONSTER_TYPE (monster_type_id)
);

CREATE TABLE IF NOT EXISTS EQUIPMENT_CASE
(
    necessary_count INT CHECK ( necessary_count > 0),
    usefulness      INT CHECK ( usefulness >= 0 AND usefulness <= 10),
    equipment_id    BIGINT,
    case_id         BIGINT,
    FOREIGN KEY (equipment_id) REFERENCES ITEM (item_id),
    FOREIGN KEY (case_id) REFERENCES CRIMINAL_CASE (case_id)
);

CREATE TABLE IF NOT EXISTS CONTENTION
(
    contention_id     BIGSERIAL PRIMARY KEY,
    date_contention    TIMESTAMP NOT NULL,
    description       TEXT,
    damage_critically INT CHECK ( damage_critically >= 0 AND damage_critically <= 10 ),
    crime_id          BIGINT UNIQUE,
    victim_id         BIGINT UNIQUE,
    FOREIGN KEY (crime_id) REFERENCES CRIME (crime_id),
    FOREIGN KEY (victim_id) REFERENCES CHARACTER (character_id)
);

CREATE TABLE IF NOT EXISTS INVESTIGATOR_CRIME
(
    crime_id        BIGINT,
    investigator_id BIGINT,
    FOREIGN KEY (crime_id) REFERENCES CRIME (crime_id),
    FOREIGN KEY (investigator_id) REFERENCES INVESTIGATOR (investigator_id)
);

