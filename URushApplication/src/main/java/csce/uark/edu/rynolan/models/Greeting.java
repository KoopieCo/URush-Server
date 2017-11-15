package csce.uark.edu.rynolan.models;

public class Greeting {
	private final long id;
	private final String name;
	private final String message;
	
	public Greeting(long id, String name, String message) {
		super();
		this.id = id;
		this.name = name;
		this.message = message;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getMessage() {
		return message;
	}
	
	
}
