package za.co.test.db.client;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class DBResponseDTO {

	private int noOfUpdates;
	
	private String student_no;
	private String first_name;
	private String last_name;
	private Date dob;
	private String cell_no;
	private String email_address;
	private BigDecimal current_score;
	private BigDecimal average_score;
	
	
	public int getNoOfUpdates() {
		return noOfUpdates;
	}
	public void setNoOfUpdates(int noOfUpdates) {
		this.noOfUpdates = noOfUpdates;
	}
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
	public BigDecimal getCurrent_score() {
		return current_score;
	}
	public void setCurrent_score(BigDecimal current_score) {
		this.current_score = current_score;
	}
	public BigDecimal getAverage_score() {
		return average_score;
	}
	public void setAverage_score(BigDecimal average_score) {
		this.average_score = average_score;
	}

}

