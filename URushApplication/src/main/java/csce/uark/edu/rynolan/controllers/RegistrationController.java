package csce.uark.edu.rynolan.controllers;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import csce.uark.edu.rynolan.configuration.JdbcConfiguration;
import csce.uark.edu.rynolan.models.Rushee;

@RestController
public class RegistrationController {
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public boolean registerRushee(@RequestBody(required=true) Rushee rushee) {
		try {
			JdbcConfiguration config = new JdbcConfiguration();
			Connection conn = config.dataSource();
			
			String queryString = "INSERT INTO rusheeInfo " + 
				     "(id, firstName, lastName, email, createdOn)" +
				     " VALUES " +
				     "(default, ?, ?, ?, localtimestamp)";
			
			PreparedStatement ps = conn.prepareStatement(queryString);
			if(!rushee.getFirstName().matches("[a-zA-Z]+") || !rushee.getLastName().matches("[a-zA-Z]+")) {
				System.out.println("Invalid Name");
				return false;
			}
			ps.setString(1, rushee.getFirstName());
			ps.setString(2, rushee.getLastName());
			ps.setString(3, rushee.getEmail());
			
			ResultSet rs = ps.executeQuery();
			rs.close();
			ps.close();
			config.closeDataSource(conn);
			return true;
		}
		catch(URISyntaxException e) {
			return false;
		}
		catch(SQLException e) {
			return false;
		}
	}
}
