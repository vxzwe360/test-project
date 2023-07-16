package za.co.test.createstudent.rest;

import java.sql.Date;

public class CreateStudentRequestDTO{
	
	private String student_no;
	private String first_name;
	private String last_name;
	private Date dob;
	private String cell_no;
	private String email_address;
	
	
	public String getStudent_no() {
		return student_no;
	}
	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getCell_no() {
		return cell_no;
	}
	public void setCell_no(String cell_no) {
		this.cell_no = cell_no;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

}
