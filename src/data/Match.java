package data;

public class Match {
	private int id;
	private int team1Id;
	private int team2Id;
	private String team1Name;
	private String team2Name;
	private int team1Goals;
	private int team2Goals;
	
	
	public Match(int id, int team1Id, int team2Id, int team1Goals, int team2Goals, String team1Name, String team2Name) {
		this.id = id;
		this.team1Id = team1Id;
		this.team2Id = team2Id;
		this.team1Goals = team1Goals;
		this.team2Goals = team2Goals;
		this.team1Name = team1Name;
		this.team2Name = team2Name;
	}
	
	public Match() {
		
	}
	
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

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	@Override
	public String toString() {	
		return "Kamp: " + team1Name + " - " + team2Name;
	}
	
}
