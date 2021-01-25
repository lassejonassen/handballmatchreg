package data;

public class Team {
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
	
	public Team(int id, String name, int matchesTotal,int matchesWon, int matchesLost, int matchesDraw,
			int goals, int goalsIn, int points, int leagueId) {
		this.id = id;
		this.name = name;
		this.matchesTotal = matchesTotal;
		this.matchesWon = matchesWon;
		this.matchesLost = matchesLost;
		this.matchesDraw = matchesDraw;
		this.goals = goals;
		this.goalsIn = goals;
		this.points = points;
		this.leagueId = leagueId;
	}
	
	public Team(String name) { 
		this.name = name; 
	}
  
	public Team() {}
	
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
		return id + " : " + name;
	}

	public String exportString() {
		return id + ", " + name + ", " + matchesTotal + ", " + matchesWon + ", " + matchesLost + ", " + matchesDraw + ", " + goals + ", " + goalsIn + ", " + points + ", " + leagueId;
	}
	
}
