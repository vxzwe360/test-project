-- Table: testschema.student_score

-- DROP TABLE IF EXISTS testschema.student_score;

CREATE TABLE IF NOT EXISTS testschema.student_score
(
    student_no text COLLATE pg_catalog."default" NOT NULL,
    current_score numeric,
    CONSTRAINT student_score_pkey PRIMARY KEY (student_no),
    CONSTRAINT fk_student_score FOREIGN KEY (student_no)
        REFERENCES testschema.student_profiles (student_no) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS testschema.student_score
    OWNER to postgres;

-- Trigger: score_changes_trigger

-- DROP TRIGGER IF EXISTS score_changes_trigger ON testschema.student_score;

CREATE TRIGGER score_changes_trigger
    AFTER UPDATE 
    ON testschema.student_score
    FOR EACH ROW
    EXECUTE FUNCTION public.score_changes_audit();