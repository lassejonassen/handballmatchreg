package logic;
import java.util.ArrayList;

import data.League;
import data.Match;
import data.Team;

public interface iMatch {
	public void createMatch(Team team, Team team2, int leagueID);
	public boolean createMatch(int leagueId, int team1Id, int team2Id);
	public ArrayList<Match> getMatchesByLeagueID(League league);
	public Match latestInsert();
	public String getOneTeam(int teamId);
	public boolean matchExists(int matchId);
	public void updateMatch(Match match);
	public void deleteMatch(Match ID);
	public void matchPlayed(Match match);
}
