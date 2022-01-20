CREATE OR REPLACE FUNCTION is_enough_evidence(serial) RETURNS bool AS
    $$
    BEGIN
        IF (SELECT count(*) FROM CLUE WHERE case_id = $1 >= 3) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    END;
    $$ LANGUAGE plpgsql;