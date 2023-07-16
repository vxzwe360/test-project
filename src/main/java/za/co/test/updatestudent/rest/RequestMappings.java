package za.co.test.updatestudent.rest;

import java.util.List;
import java.util.Objects;

import za.co.test.db.client.DBResponseDTO;

public class RequestMappings {
	
	public DBResponseDTO getRequest (List<DBResponseDTO> userExisting, UpdateStudentRequestDTO updateReq) {
		
		DBResponseDTO response = userExisting.get(0);
		
		if(Objects.nonNull(updateReq.getCell_no())) {
			response.setCell_no(updateReq.getCell_no());
		}
		
		if(Objects.nonNull(updateReq.getDob())) {

		response.setDob(updateReq.getDob());
		}
		if(Objects.nonNull(updateReq.getEmail_address())) {

		response.setEmail_address(updateReq.getEmail_address());
		}
		if(Objects.nonNull(updateReq.getFirst_name())) {

		response.setFirst_name(updateReq.getFirst_name());
		}
		if(Objects.nonNull(updateReq.getLast_name())) {

		response.setLast_name(updateReq.getLast_name());
		}
		response.setStudent_no(response.getFirst_name() + response.getLast_name());

			
		return response;
		
	}

}
