package csce.uark.edu.rynolan.models;

import java.sql.Timestamp;

public class Rushee {
	private int id;
	private String firstName, lastName;
	private String universityYear;
	private String email;
	private Timestamp timestamp;
	
	public Rushee(int id, String firstName, String lastName, String universityYear, String email, Timestamp timestamp) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.universityYear = universityYear;
		this.email = email;
		this.setTimestamp(timestamp);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUniversityYear() {
		return universityYear;
	}
	public void setUniversityYear(String universityYear) {
		this.universityYear = universityYear;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
