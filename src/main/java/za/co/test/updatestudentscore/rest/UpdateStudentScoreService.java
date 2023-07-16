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
		

		
		List<DBResponseDTO> student = new ArrayList<DBResponseDTO>();
		try {
			requiredField(request.getStudent_no(), "student_no");

			if(request.getScore().compareTo(BigDecimal.valueOf(100)) == 1) {
				response.setResultMsg("Score can not be greater than 100");
				return response;
			};
			
			
			if(request.getScore().compareTo(BigDecimal.valueOf(0)) == -1) {
				response.setResultMsg("Score can not be less than 0");
				return response;
			};
			
			try {
			 student = sqlClient.retrieveStudent(request.getStudent_no(), null, null, null);
			if (Objects.isNull(student) || student.size()==0) {
				response.setResultMsg("No student found in database");
				return response;
			}
			BigDecimal avarageScoreCalculated = (student.get(0).getAverage_score().add(request.getScore())).divide(BigDecimal.valueOf(2)) ;
			sqlClient.updateStudent(student.get(0).getStudent_no(), student.get(0).getStudent_no(), student.get(0).getFirst_name(), student.get(0).getLast_name(),
					student.get(0).getDob(), student.get(0).getCell_no(), student.get(0).getEmail_address(), 
					request.getScore(), avarageScoreCalculated);
				
				response.setResultMsg("Success");
			}
			catch (EmptyResultDataAccessException e) {
				response.setResultMsg("No student found in database");
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
