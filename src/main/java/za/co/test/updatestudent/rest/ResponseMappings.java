package za.co.test.updatestudent.rest;

import java.util.ArrayList;
import java.util.List;

import za.co.test.db.client.DBResponseDTO;


public class ResponseMappings {
	
	public List<StudentProfiles> getResponse(List<DBResponseDTO> list) {
		
		List<StudentProfiles> studentProfiles = new ArrayList<StudentProfiles>();	
		for(DBResponseDTO row : list){
			StudentProfiles sp = new StudentProfiles();
			
			sp.setAverage_score(row.getAverage_score());
			sp.setCell_no(row.getCell_no());
			sp.setCurrent_score(row.getCurrent_score());
			sp.setDob(row.getDob());
			sp.setEmail_address(row.getEmail_address());
			sp.setFirst_name(row.getFirst_name());
			sp.setLast_name(row.getLast_name());
			sp.setStudent_no(row.getStudent_no());

			studentProfiles.add(sp);
		}
		return studentProfiles;
		
	
	
	}
}
