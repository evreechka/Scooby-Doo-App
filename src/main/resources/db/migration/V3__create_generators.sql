CREATE OR REPLACE FUNCTION generate_monster() RETURNS VOID AS
$$
declare
    chars                   char[]           = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    monster_name            text             = '';
    monster_description     text             = '';
    monster_weight          double precision = 0.0;
    monster_height          double precision = 0.0;
    monster_internet_inform text             = '';
    monster_type_id         bigint           = 0;
    length                  int              = 0;
begin
    length = round(random() * 40);
    for i in 1..length
        loop
            monster_name := monster_name || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    length = round(random() * 100);
    for i in 1..length
        loop
            monster_description := monster_description || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    length = round(random() * 50);
    for i in 1..length
        loop
            monster_internet_inform :=
                        monster_internet_inform || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    monster_weight = random() * 500;
    monster_height = random() * 10;
    monster_type_id = round(random() * 18) + 1;
    INSERT INTO MONSTER (name, description, weight, height, internet_inform, type_id)
    VALUES (monster_name, monster_description, monster_weight, monster_height, monster_internet_inform,
            monster_type_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_character() RETURNS VOID AS
$$
declare
    sex_types        text ARRAY DEFAULT ARRAY ['FEMALE', 'MALE'];
    system_roles     text ARRAY DEFAULT ARRAY ['USER', 'SHERIFF'];
    chars            char[] = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    char_name        text   = '';
    char_surname     text   = '';
    char_age         int    = 0;
    char_sex         sex_type;
    char_system_role system_role_type;
    length           int    = 0;
begin
    length = round(random() * 100);
    for i in 1..length
        loop
            char_name := char_name || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    length = round(random() * 200);
    for i in 1..length
        loop
            char_surname := char_surname || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    char_system_role = system_roles[round(random()) + 1]::system_role_type;
    char_age = round(random() * 118) + 1;
    char_sex = sex_types[round(random()) + 1]::sex_type;
    INSERT INTO CHARACTER (name, surname, age, sex, system_role)
    VALUES (char_name, char_surname, char_age, char_sex, char_system_role);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_address() RETURNS VOID AS
$$
declare
    chars         char[] = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    adr_city      text   = ' ';
    adr_avenue    text   = ' ';
    adr_house_num int    = 0;
    length        int    = 0;
begin
    length = round(random() * 1000);
    for i in 1..length
        loop
            adr_city := adr_city || chars[1 + round(random() * (array_length(chars, 1) - 1))];
            adr_avenue := adr_avenue || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    adr_house_num = round(random() * 500 + 1);
    INSERT INTO ADDRESS (city, avenue, house_num)
    VALUES (adr_city, adr_avenue, adr_house_num);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_living_place() RETURNS VOID AS
$$
declare
    addr_id int;
    char_id int;
begin
    addr_id = round(random() * 49999) + 1;
    char_id = round(random() * 19999) + 1;
    INSERT INTO LIVING_PLACE (address_id, character_id) VALUES (addr_id, char_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_crime_scene() RETURNS VOID AS
$$
declare
    place_types text ARRAY DEFAULT ARRAY ['PUBLIC', 'PRIVATE_HOUSE', 'COMPANY_BUILDING', 'STREET'];
    chars       char[] = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    scene_name  text   = '';
    scene_place place_type;
    addr_id     int    = 0;
begin
    for i in 1 .. 100
        loop
            scene_name := scene_name || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    scene_place = place_types[round(random() * 3) + 1]::place_type;
    while true
        loop
            addr_id = round(random() * 49999) + 1;
            if ((SELECT count(*) FROM CRIME_SCENE WHERE address_id = addr_id) = 0::bigint) then
                exit;
            end if;
        end loop;
    INSERT INTO CRIME_SCENE (name, place, address_id) VALUES (scene_name, scene_place, addr_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_crime() RETURNS VOID AS
$$
declare
    status_types     text ARRAY DEFAULT ARRAY ['ACTIVE', 'POSTPON', 'CLOSED'];
    chars            char[]           = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    status           crime_status_type;
    crime_descr      text ;
    crime_fee        double precision = 0.0;
    crime_sheriff_id bigint           = 0;
begin
    for i in 1 .. 400
        loop
            crime_descr := crime_descr || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    crime_fee = (random() + 1) * 5000;
    status = status_types[round(random() * 2) + 1]::crime_status_type;
    while true
        loop
            crime_sheriff_id = round(random() * 19999) + 1;
            if ((SELECT system_role FROM CHARACTER WHERE character_id = crime_sheriff_id) = 'SHERIFF') then
                exit;
            end if;
        end loop;
    if crime_sheriff_id = 0 then crime_sheriff_id = NULL; end if;
    INSERT INTO CRIME(crime_status, description, fee, sheriff_id)
    VALUES (status, crime_descr, crime_fee, crime_sheriff_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_crime_visit() RETURNS VOID AS
$$
declare
    crime_visit_num  int = 0;
    crime_severity   int = 0;
    crime_date_visit timestamp;
    cr_id            int = 0;
    sc_id            int = 0;
begin
    crime_visit_num = round(random() * 9) + 1;
    crime_severity = round(random() * 10);
    crime_date_visit = now();
    cr_id = round(random() * 4999) + 1;
    sc_id = round(random() * 5999) + 1;
    INSERT INTO CRIME_VISIT(visit_num, severity_destruction, date_visit, crime_id, scene_id)
    VALUES (crime_visit_num, crime_severity, crime_date_visit, cr_id, sc_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_victim() RETURNS VOID AS
$$
declare
    chars             char[]    = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    victim_indication text      = '';
    victim_date_indic timestamp = now();
    vis_id            bigint;
    char_id           bigint;
begin
    for i in 1..500
        loop
            victim_indication := victim_indication || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    vis_id = round(random() * 7999) + 1;
    char_id = round(random() * 19999) + 1;
    INSERT INTO VICTIM (indication, date_indication, visit_id, character_id)
    VALUES (victim_indication, victim_date_indic, vis_id, char_id);

end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_suspect() RETURNS VOID AS
$$
declare
    chars      char[] = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    sus_motive text   = '';
    sus_involv int    = 0;
    vis_id     bigint = 0;
    char_id    bigint = 0;
begin
    for i in 1..500
        loop
            sus_motive := sus_motive || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    sus_involv = (round(random() * 20)) + 1;
    vis_id = round(random() * 7999) + 1;
    char_id = round(random() * 19999) + 1;
    INSERT INTO SUSPECT (motive, involvement, visit_id, character_id)
    VALUES (sus_motive, sus_involv, vis_id, char_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_criminal_case() RETURNS VOID AS
$$
declare
    case_punish  punishment_type;
    case_sever   bigint = 0;
    mon_id       bigint = 0;
    susp_id      bigint = 0;
    cr_id        bigint = 0;
    punish_types text ARRAY DEFAULT ARRAY ['NONE', 'PUBLIC_WORKS', 'FINE', 'ARREST'];
begin
    case_punish = punish_types[round(random() * 3) + 1]::punishment_type;
    case_sever = round(random() * 10);
    while true
        loop
            mon_id = round(random() * 9999 + 1);
            if ((SELECT count(*) FROM CRIMINAL_CASE WHERE monster_id = mon_id) = 0::bigint) then
                exit;
            end if;
        end loop;
    susp_id = round(random() * 8999) + 1;
    cr_id = round(random() * 4999) + 1;
    INSERT INTO CRIMINAL_CASE(punishment, severity_case, monster_id, quilt_id, crime_id)
    VALUES (case_punish, case_sever, mon_id, susp_id, cr_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_clue() RETURNS VOID AS
$$
declare
    chars        char[] = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    clue_name    text   = '';
    clue_descr   text   = '';
    vis_id       bigint = 0;
    clue_case_id bigint = 0;
begin
    for i in 1..20
        loop
            clue_name := clue_name || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    for i in 1..500
        loop
            clue_descr := clue_descr || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    vis_id = round(random() * 7999) + 1;
    clue_case_id = round(random() * 9999) + 1;
    INSERT INTO CLUE (name, description, visit_id, case_id) VALUES (clue_name, clue_descr, vis_id, clue_case_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_suspect_clue() RETURNS VOID AS
$$
declare
    cl_id   bigint = 0;
    susp_id bigint = 0;
begin
    cl_id = round(random() * 19999) + 1;
    susp_id = round(random() * 8999) + 1;
    INSERT INTO SUSPECT_CLUE (clue_id, suspect_id) VALUES (cl_id, susp_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_visit_participant() RETURNS VOID AS
$$
declare
    visit_roles_types text ARRAY DEFAULT ARRAY ['CLUE_SEARCHER', 'VICTIM_INTERVIEW', 'CRIME_SCENE_INSPECTOR', 'BAIT', 'BOBBY_TRAPS', 'TRAP_MANAGER'];
    role              visit_role_type;
    vis_id            bigint = 0;
    inv_id            bigint = 0;
begin
    role = visit_roles_types[round(random() * 5) + 1]::visit_role_type;
    vis_id = round(random() * 7999) + 1;
    inv_id = round(random() * 4) + 1;
    INSERT INTO VISIT_PARTICIPANT (visit_role, visit_id, participant_id) VALUES (role, vis_id, inv_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_case_order() RETURNS VOID AS
$$
declare
    order_date   timestamp        = now();
    deliver_date timestamp;
    status       order_status_type;
    cost         double precision = 0.0;
    cs_id        bigint           = 0;
    inv_id       bigint           = 0;
    status_types text ARRAY DEFAULT ARRAY ['NOT_CONFIRMED', 'ON_WAY', 'READY'];
begin
    status = status_types[round(random() * 2) + 1]::order_status_type;
    cost = random() * 9999 + 1;
    cs_id = round(random() * 9999) + 1;
    inv_id = round(random() * 4) + 1;
    deliver_date = now();
    INSERT INTO CASE_ORDER(date_order, date_deliver, order_status, total_cost, case_id, orderer_id)
    VALUES (order_date, deliver_date, status, cost, cs_id, inv_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_item_cart() RETURNS VOID AS
$$
declare
    count             int       = 0;
    item_date_receipt timestamp = now();
    it_id             bigint    = 0;
    ord_id            bigint    = 0;
begin
    count = round(random() * 49) + 1;
    it_id = round(random() * 12) + 1;
    ord_id = round(random() * 4999) + 1;
    INSERT INTO ITEM_CART (total_count, date_receipt, item_id, order_id)
    VALUES (count, item_date_receipt, it_id, ord_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_inventory() RETURNS VOID AS
$$
declare
    available  bool      = true;
    date_depos timestamp = now();
    it_id      bigint    = 0;
    check_av   int       = round(random());
begin
    if (check_av = 0) then
        available = false;
    end if;
    it_id = round(random() * 12) + 1;
    INSERT INTO INVENTORY (is_available, deposit_date, item_id)
    VALUES (available, date_depos, it_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_history() RETURNS VOID AS
$$
declare
    take        timestamp = now();
    return_time timestamp = now();
    serial_num  bigint    = 0;
    inv_id      bigint    = 0;
begin
    serial_num = round(random() * 499) + 1;
    inv_id = round(random() * 4) + 1;
    INSERT INTO HISTORY(time_take, time_return, item_serial_num, investigator_id)
    VALUES (take, return_time, serial_num, inv_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_trap_case() RETURNS VOID AS
$$
declare
    chars          char[] = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    trap_name      text   = '';
    useful         int    = 0;
    selected       bool   = true;
    cs_id          bigint = 0;
    check_selected int    = round(random());
begin
    for i in 1..20
        loop
            trap_name := trap_name || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    useful = round(random() * 10);
    if (check_selected = 0) then
        selected = false;
    end if;
    cs_id = round(random() * 9999) + 1;
    INSERT INTO TRAP_CASE(name, usefulness, is_selected, case_id)
    VALUES (trap_name, useful, selected, cs_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_trap_item() RETURNS VOID AS
$$
declare
    count int    = 0;
    it_id bigint = 0;
    tr_id bigint = 0;
begin
    count = round(random() * 15) + 1;
    it_id = round(random() * 12) + 1;
    tr_id = round(random() * 9999) + 1;
    INSERT INTO TRAP_ITEM (necessary_count, item_id, trap_id) VALUES (count, it_id, tr_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_item_monster() RETURNS VOID AS
$$
declare
    it_id   bigint = 0;
    type_id bigint = 0;
begin
    it_id = round(random() * 12) + 1;
    type_id = round(random() * 18) + 1;
    INSERT INTO ITEM_MONSTER (item_id, monster_type_id) VALUES (it_id, type_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_equipment_case() RETURNS VOID AS
$$
declare
    count  int    = 0;
    useful int    = 0;
    eq_id  bigint = 0;
    cs_id  bigint = 0;
begin
    count = round(random() * 5) + 1;
    useful = round(random() * 10);
    while true
        loop
            eq_id = round(random() * 12) + 1;
            if ((SELECT type FROM ITEM WHERE eq_id = item_id) = 'EQUIPMENT') then
                exit;
            end if;
        end loop;
    cs_id = round(random() * 9999) + 1;
    INSERT INTO EQUIPMENT_CASE (necessary_count, usefulness, equipment_id, case_id)
    VALUES (count, useful, eq_id, cs_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_contention() RETURNS VOID AS
$$
declare
    chars     char[]    = '{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, у, z}';
    date_cont timestamp = now();
    descr     text      = '';
    damage    int       = 0;
    cr_id     bigint    = 0;
    vict_id   bigint    = 0;
begin
    for i in 1..200
        loop
            descr := descr || chars[1 + round(random() * (array_length(chars, 1) - 1))];
        end loop;
    damage = round(random() * 10);
    while true
        loop
            cr_id = round(random() * 4999) + 1;
            if ((SELECT count(*) FROM CONTENTION WHERE crime_id = cr_id) = 0) then
                exit;
            end if;
        end loop;
    while true
        loop
            vict_id = round(random() * 19999) + 1;
            if ((SELECT count(*) FROM CONTENTION WHERE victim_id = vict_id) = 0) then
                exit;
            end if;
        end loop;
    INSERT INTO CONTENTION (date_contention, description, damage_critically, crime_id, victim_id)
    VALUES (date_cont, descr, damage, cr_id, vict_id);
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_investigator_crime() RETURNS VOID AS
$$
declare
    cr_id  bigint = 0;
    inv_id bigint = 0;
begin
    cr_id = round(random() * 4999) + 1;
    inv_id = round(random() * 4) + 1;
    INSERT INTO INVESTIGATOR_CRIME (crime_id, investigator_id) VALUES (cr_id, inv_id);
end;
$$ LANGUAGE plpgsql;
