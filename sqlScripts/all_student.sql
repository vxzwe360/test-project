-- View: testschema.all_students

-- DROP VIEW testschema.all_students;

CREATE OR REPLACE VIEW testschema.all_students
 AS
 SELECT sp.student_no,
    sp.first_name,
    sp.last_name,
    sp.dob,
    sp.cell_no,
    sp.email_address,
    sc.current_score
   FROM testschema.student_profiles sp,
    testschema.student_score sc
  WHERE sp.student_no = sc.student_no;

ALTER TABLE testschema.all_students
    OWNER TO postgres;
COMMENT ON VIEW testschema.all_students
    IS 'select all students with their latest score average';

