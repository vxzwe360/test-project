package za.co.test.deletestudent.rest;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.test.db.client.DBResponseDTO;
import za.co.test.db.client.SqlClient;

@Component
public class DeleteStudentService {

	@Autowired
	public SqlClient sqlClient;


	@SuppressWarnings("null")
	public DeleteStudentResponseDTO deleteStudent(DeleteStudentRequestDTO request) throws Exception {
		
		DeleteStudentResponseDTO response = new DeleteStudentResponseDTO();
		try {

			List<DBResponseDTO> student = sqlClient.retrieveStudent(request.getStudent_no(), null, null, null);
			
			if (Objects.nonNull(student) && student.size()>0) {
				sqlClient.deleteStudentProfile(request.getStudent_no());
				response.setResultMsg("Success");
			}
			else {
				response.setResultMsg("student does not exist");
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
