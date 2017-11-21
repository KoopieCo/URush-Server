package csce.uark.edu.rynolan.controllers;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import csce.uark.edu.rynolan.configuration.JdbcConfiguration;
import csce.uark.edu.rynolan.models.Greeting;

@RestController
public class GreetingController {
	public static final String messageTemplate = "Hello, %s!";
	public final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greet")
	public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) throws URISyntaxException, SQLException {
		JdbcConfiguration config = new JdbcConfiguration();
		Connection conn = config.dataSource();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM rusheeInfo");
		while(rs.next()) {
			System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
		}
		rs.close();
		st.close();
		config.closeDataSource(conn);
		
		
		return new Greeting(counter.incrementAndGet(), name, String.format(messageTemplate, name));
	}
}
