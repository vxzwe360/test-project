package za.co.test.searchstudent.rest;

import java.util.List;

public class SearchStudentResponseDTO {
	

	private String resultMsg;
	private List<StudentProfiles> studentProfiles;
	
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public List<StudentProfiles> getStudentProfiles() {
		return studentProfiles;
	}
	public void setStudentProfiles(List<StudentProfiles> studentProfiles) {
		this.studentProfiles = studentProfiles;
	}
	
}
