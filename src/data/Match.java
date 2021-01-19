package data;

public class Match {
	private int matchID;
	private String team1Name;
	private String team2Name;
	private int team1Goals;
	private int team2Goals;
	private String divider = "-";
	
	public Match(int id, int team1Goals, int team2Goals, String team1Name, String team2Name) {
		this.matchID = id;
		this.team1Goals = team1Goals;
		this.team2Goals = team2Goals;
		this.team1Name = team1Name;
		this.team2Name = team2Name;
	}
	public Match() {}
	
	public String getTeam1Name() {
		return team1Name;
	}

	public void setTeam1Name(String team1Name) {
		this.team1Name = team1Name;
	}

	public String getTeam2Name() {
		return team2Name;
	}

	public void setTeam2Name(String team2Name) {
		this.team2Name = team2Name;
	}
	
	public int getMatchID() {
		return matchID;
	}
	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}
	public int getTeam1Goals() {
		return team1Goals;
	}
	
	public void setTeam1Goals(int team1Goals) {
		this.team1Goals = team1Goals;
	}
	
	public int getTeam2Goals() {
		return team2Goals;
	}
	
	public void setTeam2Goals(int team2Goals) {
		this.team2Goals = team2Goals;
	}
	
	public String getDivider() {
		return divider;
	}
	public void setDivider(String divider) {
		this.divider = divider;
	}
	@Override
	public String toString() {
		return matchID + " " + team1Name + " " + divider + " " + team2Name;
	}
	
	
	
	
}
