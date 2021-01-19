package logic;


import java.util.ArrayList;

import data.DataLayer;
import data.League;
import data.Match;
import data.Team;

public class MatchImpl implements iMatch {
	DataLayer dataLayer = new DataLayer();
	
	public void createMatch(Team team, Team team2, int leagueID) {
		dataLayer.createMatch(team.getId(), team2.getId(), leagueID);
	}
	public void createSuspension(Match match, int teamID, String time) {
		dataLayer.createSuspension(match.getId(), teamID, time);
	}
	
	public void deleteSuspension(Match match, int teamID, int suspensionID) {
		dataLayer.deleteSuspension(match.getId(), teamID, suspensionID);
	}
	
	public void deleteMatch(Match match) {
		dataLayer.deleteMatch(match.getMatchID());
	}
	
	public ArrayList<Match> getMatchesByLeagueID(League league) {
		return dataLayer.getAllMatchesByLeagueID(league.getId());
	}
	
	
	public void getAllMatchesTest() {
		dataLayer.getAllMatchesTest();
	}
	
	public void updateMatch(Match match, int team1Goals, int team2Goals) {
		dataLayer.updateMatch(match.getMatchID(), team1Goals, team2Goals);
	}
}
