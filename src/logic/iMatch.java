package logic;

import data.Match;
import data.Team;
public interface iMatch {
	
	public void createMatch(Team team, Team team2);
	public void createSuspension(Match ID, Team teamID, String time);
	public void deleteMatch(Match ID);
//	public void updateMatch(Match ID);
}
