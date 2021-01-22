package data;

public class Match {
	private int matchID;
	private int team1Id;
	private int team2Id;
	private String team1Name;
	private String team2Name;
	private int team1Goals;
	private int team2Goals;
	private String divider = "-";
	private String played;
	
	public Match(int id, int team1Goals, int team2Goals, String team1Name, String team2Name, int team1Id, int team2Id, String played) {
		this.matchID = id;
		this.team1Goals = team1Goals;
		this.team2Goals = team2Goals;
		this.team1Name = team1Name;
		this.team2Name = team2Name;
		this.team1Id = team1Id;
		this.team2Id = team2Id;
		this.played = played;
	}
	public String getPlayed() {
		return played;
	}
	public void setPlayed(String played) {
		this.played = played;
	}
	public Match() {}
	
	public String getTeam1Name() {
		return team1Name;
	}

	public void setTeam1Name(String team1Name) {
		this.team1Name = team1Name;
	}

	public int getTeam1Id() {
		return team1Id;
	}
	
	public void setTeam1Id(int team1Id) {
		this.team1Id = team1Id;
	}
	
	public int getTeam2Id() {
		return team2Id;
	}
	
	public void setTeam2Id(int team2Id) {
		this.team2Id = team2Id;
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
