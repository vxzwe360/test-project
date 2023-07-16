package za.co.test.getallstudents.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import za.co.test.db.client.DBResponseDTO;
import za.co.test.db.client.SqlClient;


@Component
public class GetAllStudentsService {


	@Autowired
	public SqlClient sqlClient;

		public GetAllStudentsResponseDTO processRequest() throws Exception{

		GetAllStudentsResponseDTO response = new GetAllStudentsResponseDTO();

		ResponseMappings rsMappings = new ResponseMappings();
		
		try {
			List<DBResponseDTO> student = sqlClient.retrieveAllStudents();
			List<StudentProfiles> mapped = rsMappings.getResponse(student);
			response.setStudentProfiles(mapped);
			response.setResultMsg("Success");
			return response;
		}
		catch (EmptyResultDataAccessException e) {
			response.setResultMsg("No records found in database");
			return response;
		}
	}
}
