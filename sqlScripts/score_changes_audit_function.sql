CREATE OR REPLACE FUNCTION score_changes_audit()
  RETURNS TRIGGER 
  AS $score_changes_trigger$ 
BEGIN
	IF ( UPDATE (testschema.student_profiles.current_score) ) THEN
		 INSERT INTO testschema.score_progression(student_no,previous_score,update_date)
		 VALUES(OLD.student_no,OLD.current_score,current_timestamp);
	END IF;
	RETURN NEW;
END;
$score_changes_trigger$  LANGUAGE PLPGSQL;