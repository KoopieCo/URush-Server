package csce.uark.edu.rynolan.controllers;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import csce.uark.edu.rynolan.configuration.JdbcConfiguration;
import csce.uark.edu.rynolan.models.Announcement;

@RestController
public class AnnouncementController {
	@RequestMapping(value = "/announcements", method = RequestMethod.GET)
	public List<Announcement> getAnnouncements(@RequestParam(value="date", required=false, defaultValue="EMPTY") String date) {
		try {
			JdbcConfiguration config = new JdbcConfiguration();
			Connection conn = config.dataSource();
			
			// TODO: Add in the date as a parameter to the query.
			String queryString = "SELECT * FROM rushevents";
			/*PreparedStatement ps = conn.prepareStatement(queryString);
			// Generate the next parameter to be an offset by date
			if() {
				System.out.println("Invalid Name");
				return null;
			}
			ps.setString(1, rushee.getFirstName());
			ps.executeUpdate();
			ps.close();
			*/
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);
			
			// Work with Result Set
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			List<Announcement> announcementList = new ArrayList<>();
			while(rs.next()) {
				Announcement temp = new Announcement();
				temp.setEventTitle(rs.getString(1));
				temp.setEventLocation(rs.getString(2));
				temp.setEventTime(rs.getTimestamp(3).toLocalDateTime().format(formatter));
				
				announcementList.add(temp);
			}
			
			rs.close();
			statement.close();
			config.closeDataSource(conn);
			
			return announcementList;
		}
		catch(URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
