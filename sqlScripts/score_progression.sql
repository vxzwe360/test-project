-- Table: testschema.score_progression

-- DROP TABLE IF EXISTS testschema.score_progression;

CREATE TABLE IF NOT EXISTS testschema.score_progression
(
    student_no text COLLATE pg_catalog."default",
    previous_score numeric,
    update_date timestamp with time zone
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS testschema.score_progression
    OWNER to postgres;