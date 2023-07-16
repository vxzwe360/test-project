package za.co.test.updatestudentscore.rest;

import java.math.BigDecimal;
import java.sql.Date;

public class UpdateStudentScoreRequestDTO{
	
	private BigDecimal score;
	private String student_no;

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getStudent_no() {
		return student_no;
	}

	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}
	
}
