package za.co.test.updatestudent.rest;

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
public class UpdateStudentService {

	@Autowired
	public SqlClient sqlClient;


	public UpdateStudentResponseDTO updateStudent(UpdateStudentRequestDTO request) throws Exception {
		
		UpdateStudentResponseDTO response = new UpdateStudentResponseDTO();
		List<DBResponseDTO> student = new ArrayList<DBResponseDTO>();
		RequestMappings reqMappings = new RequestMappings();
		
		
		try {
			requiredField(request.getStudent_no(), "student_no");

			 student = sqlClient.retrieveStudent(request.getStudent_no(), null, null, null);
			 
			if (Objects.isNull(student) || student.size()==0) {
				response.setResultMsg("No student found in database");
				return response;
			}
			DBResponseDTO update = reqMappings.getRequest(student, request);
			sqlClient.updateStudent(request.getStudent_no(),update.getStudent_no(), update.getFirst_name(), update.getLast_name(),
					update.getDob(), update.getCell_no(), update.getEmail_address(), 
					update.getCurrent_score(), update.getAverage_score());
			
			response.setResultMsg("Success");
			
		}
		catch (EmptyResultDataAccessException e) {
			response.setResultMsg("No student found in database");
			return response;
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
