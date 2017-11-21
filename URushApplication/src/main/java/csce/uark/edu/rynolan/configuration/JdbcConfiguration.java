package csce.uark.edu.rynolan.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcConfiguration {
	@Bean
	public Connection dataSource() throws URISyntaxException, SQLException {
		URI dbUri = new URI("postgres://iymremslgjbljm:f7137e79f9a25ccabd798fcb17c41fd79eed44bd75f30219f031fe335901c50d@ec2-204-236-236-188.compute-1.amazonaws.com:5432/da0e0vuv5v7bdp");
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require&user=" + username + "&password=" + password;
		return DriverManager.getConnection(dbUrl);
	}
	
	public void closeDataSource(Connection c) throws SQLException {
		c.close();
	}
}
