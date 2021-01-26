package data;

public class Suspension {
	private int id;
	private int matchId;
	private int matchTime;
	private int teamId;
	
	public Suspension(int id, int matchId, int timeStamp, int teamId) {
		this.id = id;
		this.matchId = matchId;
		this.matchTime = timeStamp;
		this.teamId = teamId;
	}
	
	
	public Suspension(int matchId, int timeStamp, int teamId) {
		this.matchId = matchId;
		this.matchTime = timeStamp;
		this.teamId = teamId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMatchId() {
		return matchId;
	}
	
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	
	public int getMatchTime() {
		return matchTime;
	}
	
	public void setMatchTime(int matchTime) {
		this.matchTime = matchTime;
	}
	
	public int getTeamId() {
		return teamId;
	}
	
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
}
