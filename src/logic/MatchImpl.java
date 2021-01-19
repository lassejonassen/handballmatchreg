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
	
	public void createSuspension(Match match, Team team, String time) {
		dataLayer.createSuspension(match.getId(), team.getId(), time);
	}
	
	public void deleteSuspension(Match match, Team team, String time) {
		dataLayer.deleteSuspension(match.getId(), team.getId(), time);
	}
	
	public void deleteMatch(Match match) {
		dataLayer.deleteMatch(match.getId());
	}
	
	public ArrayList<Match> getAllMatches(League league) {
		return dataLayer.getAllMatches(league.getId());
	}
	
	public ArrayList<Match> getAllMatches(int league) {
		return dataLayer.getAllMatches(league);
	}
	
	public void getAllMatchesTest() {
		dataLayer.getAllMatchesTest();
	}
	
	public void updateMatch(Match match, int team1Goals, int team2Goals) {
		dataLayer.updateMatch(match.getId(), team1Goals, team2Goals);
	}
}
