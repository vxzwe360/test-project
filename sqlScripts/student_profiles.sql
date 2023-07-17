-- Table: testschema.student_profiles

-- DROP TABLE IF EXISTS testschema.student_profiles;

CREATE TABLE IF NOT EXISTS testschema.student_profiles
(
    student_no text COLLATE pg_catalog."default" NOT NULL,
    first_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    dob date,
    cell_no text COLLATE pg_catalog."default",
    email_address text COLLATE pg_catalog."default",
    CONSTRAINT student_profiles_pkey PRIMARY KEY (student_no)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS testschema.student_profiles
    OWNER to postgres;