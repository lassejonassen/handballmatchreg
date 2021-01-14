package logic;

import data.Match;
import data.Team;
public interface iMatch {
	
	public void createMatch(Team team, Team team2);
	public void createSuspension(Match match, Team team, String time);
	public void deleteMatch(Team team, Team team2);
	public void updateMatch(Team team, Team team2);
}
