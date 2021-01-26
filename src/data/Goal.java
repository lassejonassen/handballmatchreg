package data;

public class Goal {
	private int id;
	private int matchId;
	private int timeStamp;
	private int teamId;
	
	public Goal(int id, int matchId, int timeStamp, int teamId) {
		this.id = id;
		this.matchId = matchId;
		this.timeStamp = timeStamp;
		this.teamId = teamId;
	}
	
	public Goal(int matchId, int timeStamp, int teamId) {
		this.matchId = matchId;
		this.timeStamp = timeStamp;
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
	
	public int getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public int getTeamId() {
		return teamId;
	}
	
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	@Override
	public String toString() {
		return "Goal [id=" + id + ", matchId=" + matchId + ", timeStamp=" + timeStamp + ", teamId=" + teamId + "]";
	}
}
