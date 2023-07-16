package za.co.test.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SqlConfig {

	//MSSQL server
	@Primary
	@Bean("mssqlProperties")
	@ConfigurationProperties(prefix = "test.connections.jdbc")
	public DataSourceProperties mssqlServerDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean("mssqlDb")
	public DataSource mssqlServerDataSource(@Qualifier("mssqlProperties") DataSourceProperties mssqlProperties) {
		return mssqlProperties.initializeDataSourceBuilder().build();
	}
	
	@Primary
	@Bean("mssqlJdbcTemplate")
	public JdbcTemplate mssqlJdbcTemplate(@Qualifier("mssqlDb")DataSource dsMSSQL) {

		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dsMSSQL);
		jdbcTemplate.setDataSource(dsMSSQL);
		return jdbcTemplate;
	}

}
