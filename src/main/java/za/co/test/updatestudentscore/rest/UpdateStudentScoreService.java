package za.co.test.updatestudentscore.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import za.co.test.db.client.DBResponseDTO;
import za.co.test.db.client.SqlClient;

@Component
public class UpdateStudentScoreService {

	@Autowired
	public SqlClient sqlClient;


	public UpdateStudentScoreResponseDTO updateStudent(UpdateStudentScoreRequestDTO request) throws Exception {
		
		UpdateStudentScoreResponseDTO response = new UpdateStudentScoreResponseDTO();
		

		
		DBResponseDTO student = new DBResponseDTO();
		try {
			requiredField(request.getStudent_no(), "student_no");
			
			 List<DBResponseDTO> studentDB = sqlClient.retrieveStudent(request.getStudent_no(), null, null, null);
			if (Objects.isNull(studentDB) || studentDB.size()==0) {
				response.setResultMsg("No student found in database");
				return response;
			}

			if(request.getScore().compareTo(BigDecimal.valueOf(100)) == 1) {
				response.setResultMsg("Score can not be greater than 100");
				return response;
			};
			
			
			if(request.getScore().compareTo(BigDecimal.valueOf(0)) == -1) {
				response.setResultMsg("Score can not be less than 0");
				return response;
			};
			
			try {
				student = sqlClient.retrieveScore(request.getStudent_no());
				if (Objects.isNull(student)) {
					sqlClient.insertStudentScore(request.getStudent_no(), request.getScore());
					response.setResultMsg("Success");
					return response;
				}
				sqlClient.updateScore(request.getStudent_no(), request.getScore());
				
				response.setResultMsg("Success");
			}
			catch (EmptyResultDataAccessException e) {
				sqlClient.insertStudentScore(request.getStudent_no(), request.getScore());
				response.setResultMsg("Success");
				return response;
			}
		}
		catch (Exception e)
		{
			response.setResultMsg(e.getMessage());
			return response;

		}
		return response;

			
	}
	
	protected void requiredField(Object object, String name) throws Exception  {
		if (isEmpty(object))
			throw new Exception("The field \"" + name + "\" is required!");
	}
	
	
	
	protected boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}

		if ((object.getClass().isAssignableFrom(String.class)) && ((String) object).length() == 0) {
			return true;
		}

		if (object.getClass().isAssignableFrom(List.class) && ((List<?>) object).size() == 0) {
			return true;
		}

		return false;
	}
	
}
