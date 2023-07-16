package za.co.test.createstudent.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import za.co.test.db.client.SqlClient;

@Component
public class CreateStudentService {

	@Autowired
	public SqlClient sqlClient;


	public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO request) throws Exception {
		
		CreateStudentResponseDTO response = new CreateStudentResponseDTO();
		try {
			requiredField(request.getFirst_name(), "first_name");
			requiredField(request.getLast_name(), "last_name");
			String student_no = request.getFirst_name() + request.getLast_name();
			sqlClient.insertStudentProfile(student_no, request.getFirst_name(), request.getLast_name(),
					request.getDob(), request.getCell_no(), request.getEmail_address());
			
			response.setResultMsg("Success");
		}
		catch (DuplicateKeyException duplicateE) {
			
			response.setResultMsg("(student_no)=("+request.getFirst_name() + request.getLast_name()+") already exists.");
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
