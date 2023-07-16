package za.co.test.db.client;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SqlClient {


	@Autowired
	@Qualifier("mssqlJdbcTemplate")
	JdbcTemplate mssqlJdbcTemplate;
	
	public List<DBResponseDTO> retrieveStudent(String student_no, String first_name, String last_name, String email_address) throws Exception {

        List<DBResponseDTO> messagStoreList = new ArrayList<>();
		String query = "SELECT student_no, first_name, last_name, dob, cell_no, email_address, current_score\r\n"
				+ "	FROM testschema.all_students where student_no= ? or first_name=? or last_name=? or email_address=?";
		try {;
		List<Map<String, Object>> rows = mssqlJdbcTemplate.queryForList(query, new Object[] { student_no, first_name, last_name, email_address });
				
		
        for (Map row : rows) {
        	DBResponseDTO obj = new DBResponseDTO();
        	BigDecimal averageScore = getAverageScore((String) row.get("STUDENT_NO"), (BigDecimal) row.get("CURRENT_SCORE"));
        	
        	obj.setCell_no((String) row.get("CELL_NO"));
        	
        	
        	obj.setAverage_score(averageScore);
        	obj.setCurrent_score((BigDecimal) row.get("CURRENT_SCORE"));
        	obj.setDob((Date) row.get("DOB"));
        	obj.setEmail_address((String) row.get("EMAIL_ADDRESS"));
        	obj.setFirst_name((String) row.get("FIRST_NAME"));
        	obj.setLast_name((String) row.get("LAST_NAME"));
        	obj.setStudent_no((String) row.get("STUDENT_NO"));


        	messagStoreList.add(obj);
        }
        
		
		return messagStoreList;
		}
		catch (EmptyResultDataAccessException e) {
			throw new Exception("No records found in database");
		}
	}
	
	
	
	public void insertStudentScore(String student_no, BigDecimal current_score) {



		int output = mssqlJdbcTemplate.update(
				"INSERT INTO testschema.student_score(student_no, current_score) VALUES (?, ?)",
				student_no,current_score);
	}
	
	
	
	public void insertStudentProfile(String student_no, String first_name, String last_name, Date dob, String cell_no,
			String email_address ) {



		int output = mssqlJdbcTemplate.update(
				"INSERT INTO testschema.student_profiles(student_no, first_name, last_name, dob, cell_no, email_address) VALUES (?, ?, ?, ?, ?, ?)",
				student_no,first_name, last_name, dob,cell_no,email_address);
		
		insertStudentScore(student_no, null);


	}
	
	
	public  void updateStudent(String old_student_no, String student_no, String first_name, String last_name, Date dob, String cell_no,
			String email_address, BigDecimal current_score, BigDecimal average_score ) {



		int output = mssqlJdbcTemplate.update(
				"UPDATE testschema.student_profiles SET student_no=?, first_name=?, last_name=?, dob=?, cell_no=?, email_address=?, current_score=?, average_score=? "
				+ "where student_no = ?",
				student_no,first_name, last_name, dob,cell_no,email_address,current_score,average_score,old_student_no);

	}
	
	public  void updateScore(String old_student_no, BigDecimal current_score) {



		int output = mssqlJdbcTemplate.update(
				"UPDATE testschema.student_score SET current_score=?"
				+ " where student_no = ?",
				current_score,old_student_no);

	}
	
	
	
	public int deleteStudentProfile(String student_no) {
		int deleteResponse = 0;
		Object[] params = {student_no};
		String deleteQuery = "DELETE FROM testschema.student_profiles where STUDENT_NO = ?"
				+ "";
		String deleteScoreQuery = "DELETE FROM testschema.student_score where STUDENT_NO = ?"
				+ "";
		try {
			 deleteResponse = mssqlJdbcTemplate.update(deleteScoreQuery, params);	
			 deleteResponse = mssqlJdbcTemplate.update(deleteQuery, params);		
			 }
		catch(Exception e) {
			e.printStackTrace();
		}
		return deleteResponse;
	}
	
	
	public DBResponseDTO retrieveScore(String student_no) {

		String query = "SELECT * FROM testschema.student_score where student_no = ?";
		
		@SuppressWarnings("deprecation")
		DBResponseDTO response = mssqlJdbcTemplate.queryForObject(query,
				new Object[] { student_no,  },
				new ScoreDBRowMapper());

		return response;
		
	}
	
	
	public BigDecimal getAverageScore(String student_no, BigDecimal current_score) {

		 List<DBResponseDTO> messagStoreList = new ArrayList<>();
			String query = "SELECT * FROM testschema.score_progression where student_no=? ";
	
			List<Map<String, Object>> rows = mssqlJdbcTemplate.queryForList(query, new Object[] { student_no});
			BigDecimal average = current_score;
			int found = 0;
			 for (Map row : rows) {
				 if (Objects.nonNull(row.get("PREVIOUS_SCORE"))) {
					 BigDecimal dbScore = (BigDecimal) row.get("PREVIOUS_SCORE");
					 average =  dbScore.add(average);
					 found++;
					 
				 }
			 }
			 if (found>0) {
				 
				 BigDecimal size  = BigDecimal.valueOf(found+1);
				 average = average.divide(size);
			 }
			 
		return average;
		
	}
	
	
	
	public List<DBResponseDTO> retrieveAllStudents() throws Exception {

        List<DBResponseDTO> messagStoreList = new ArrayList<>();
		String query = "SELECT student_no, first_name, last_name, dob, cell_no, email_address, current_score\r\n"
				+ "	FROM testschema.all_students;";
		
		List<Map<String, Object>> rows = mssqlJdbcTemplate.queryForList(query);
				
		
        for (Map row : rows) {
        	DBResponseDTO obj = new DBResponseDTO();

        	BigDecimal averageScore = getAverageScore((String) row.get("STUDENT_NO"), (BigDecimal) row.get("CURRENT_SCORE"));
        	obj.setCell_no((String) row.get("CELL_NO"));
        	obj.setAverage_score(averageScore);
        	obj.setCurrent_score((BigDecimal) row.get("CURRENT_SCORE"));
        	obj.setDob((Date) row.get("DOB"));
        	obj.setEmail_address((String) row.get("EMAIL_ADDRESS"));
        	obj.setFirst_name((String) row.get("FIRST_NAME"));
        	obj.setLast_name((String) row.get("LAST_NAME"));
        	obj.setStudent_no((String) row.get("STUDENT_NO"));


        	messagStoreList.add(obj);
        }
        
		
		return messagStoreList;
		}
	

}
