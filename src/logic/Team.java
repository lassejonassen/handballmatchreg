package logic;

public class Team {
	/*
	 * Author: Lucas Elley
	 * Date: 12/01/2021
	 */

	private int teamId;
	private String name;
	private int goals;
	private int point;
	
	
	public Team (String name) {
		this.name = name;
	}
	
	public int getId() {
		return teamId;
	}
	public void setId(int id) {
		this.teamId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
}
