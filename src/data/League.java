package data;

/*
 *  Author: Lucas Elley
 *  Date: 12/01/2021
 */
public class League {
	private int id;
	private String name;
	
	public League(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return id + " " + name;
	}
	
}
