package za.co.test.searchstudent.rest;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import za.co.test.db.client.DBResponseDTO;
import za.co.test.db.client.SqlClient;


@Component
public class SearchStudentService {


	@Autowired
	public SqlClient sqlClient;

		public SearchStudentResponseDTO searchStudent(SearchStudentRequestDTO search) throws Exception{

		SearchStudentResponseDTO response = new SearchStudentResponseDTO();

		ResponseMappings rsMappings = new ResponseMappings();
		
		try {
			List<DBResponseDTO> student = sqlClient.retrieveStudent(search.getStudent_no(), search.getFirst_name(), search.getLast_name(), search.getEmail_address());
			List<StudentProfiles> mapped = rsMappings.getResponse(student);
			
			if (Objects.isNull(mapped) || mapped.size() == 0) {
				response.setResultMsg("No user found in the database");
				return response;

			}
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
