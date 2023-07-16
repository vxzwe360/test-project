package za.co.test.getallstudents.rest;

import java.util.List;

public class GetAllStudentsResponseDTO {
	

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
