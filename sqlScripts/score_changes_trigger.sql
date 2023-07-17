create trigger score_changes_trigger after update on testschema.student_score 
for each row execute Procedure score_changes_audit();
