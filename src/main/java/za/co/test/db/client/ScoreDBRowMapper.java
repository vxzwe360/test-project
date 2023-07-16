package za.co.test.db.client;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ScoreDBRowMapper implements RowMapper<DBResponseDTO> {

	@Override
	public DBResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DBResponseDTO response = new DBResponseDTO();
		
		response.setStudent_no(rs.getString("student_no"));
		response.setCurrent_score(rs.getBigDecimal("current_score"));

		return response;
	}
}

