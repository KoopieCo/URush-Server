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
	public ResultMessage registerRushee(@RequestBody(required=true) Rushee rushee) {
		try {
			JdbcConfiguration config = new JdbcConfiguration();
			Connection conn = config.dataSource();
			
			String queryString = "INSERT INTO rusheeInfo " + 
				     "(id, firstName, lastName, classYear, email, createdOn)" +
				     " VALUES " +
				     "(default, ?, ?, ?, ?, localtimestamp)";
			
			PreparedStatement ps = conn.prepareStatement(queryString);
			if(!rushee.getFirstName().matches("[a-zA-Z]+") || !rushee.getLastName().matches("[a-zA-Z]+")) {
				System.out.println("Invalid Name");
				return new ResultMessage(400,"Invalid Name");
			}
			ps.setString(1, rushee.getFirstName());
			ps.setString(2, rushee.getLastName());
			ps.setString(3, rushee.getUniversityYear());
			ps.setString(4, rushee.getEmail());
			
			ps.executeUpdate();
			
			ps.close();
			config.closeDataSource(conn);
			return new ResultMessage(200, "Added Successfully!");
		}
		catch(URISyntaxException e) {
			e.printStackTrace();
			return  new ResultMessage(400,"URI Error");
		}
		catch(SQLException e) {
			e.printStackTrace();
			return  new ResultMessage(400,"SQL Error");
		}
	}
	
	private class ResultMessage {
		private int resultCode;
		private String resultMessage;
		
		public ResultMessage() {}
		
		public ResultMessage(int resultCode, String resultMessage) {
			super();
			this.resultCode = resultCode;
			this.resultMessage = resultMessage;
		}

		public int getResultCode() {
			return resultCode;
		}

		public void setResultCode(int resultCode) {
			this.resultCode = resultCode;
		}

		public String getResultMessage() {
			return resultMessage;
		}

		public void setResultMessage(String resultMessage) {
			this.resultMessage = resultMessage;
		}
	}
}
