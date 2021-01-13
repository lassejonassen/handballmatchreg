package data;

public class Team {
	/*
	 * Author: Lucas Elley
	 * Date: 12/01/2021
	 */
	private int id; 
	private String name;
	private int matchesTotal;
	private int matchesWon;
	private int matchesLost;
	private int matchesDraw;
	private int goals;
	private int goalsIn;
	private int points;
	private int leagueId;
	
	public Team() {}
	
	public Team (String name) {
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
	public int getMatchesTotal() {
		return matchesTotal;
	}
	public void setMatchesTotal(int matchesTotal) {
		this.matchesTotal = matchesTotal;
	}
	public int getMatchesWon() {
		return matchesWon;
	}
	public void setMatchesWon(int matchesWon) {
		this.matchesWon = matchesWon;
	}
	public int getMatchesLost() {
		return matchesLost;
	}
	public void setMatchesLost(int matchesLost) {
		this.matchesLost = matchesLost;
	}
	public int getMatchesDraw() {
		return matchesDraw;
	}
	public void setMatchesDraw(int matchesDraw) {
		this.matchesDraw = matchesDraw;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int getGoalsIn() {
		return goalsIn;
	}
	public void setGoalsIn(int goalsIn) {
		this.goalsIn = goalsIn;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	@Override
	public String toString() {
		return id + " " + name;
	}
	
	
}
