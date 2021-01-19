package logic;
import java.util.ArrayList;

import data.League;
import data.Match;
import data.Team;

public interface iMatch {
	public void createMatch(Team team, Team team2, int leagueID);
	public void createSuspension(Match ID, Team teamID, String time);
	public void deleteSuspension(Match ID, Team teamID, String time);
	public void deleteMatch(Match ID);
	public void updateMatch(Match match, int team1Goals, int team2Goals);
	
	public ArrayList<Match> getMatchesByLeagueID(League league);
}
