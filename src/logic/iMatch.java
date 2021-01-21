package logic;
import java.util.ArrayList;

import data.League;
import data.Match;
import data.Team;

public interface iMatch {
	public void createMatch(Team team, Team team2, int leagueID);
	public ArrayList<Match> getMatchesByLeagueID(League league);
	public void updateMatch(Match match);
	public void deleteMatch(Match ID);
}
