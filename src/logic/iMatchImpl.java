package logic;


import java.util.ArrayList;

import data.DataLayer;
import data.Match;
import data.Team;

public class iMatchImpl implements iMatch {
	DataLayer dataLayer = new DataLayer();
	
	public void createMatch(Team team, Team team2, int leagueID) {
		dataLayer.createMatch(team.getId(), team2.getId(), leagueID);
	}
	
	public void createSuspension(Match match, Team team, String time) {
		dataLayer.createSuspension(match.getId(), team.getId(), time);
	}
	
	public void deleteMatch(Match match) {
		dataLayer.deleteMatch(match.getId());
	}
	
	public ArrayList<Match> getAllMatches(int leagueID) {
		return dataLayer.getAllMatches(leagueID);
	}
	
	public void getAllMatchesTest() {
		dataLayer.getAllMatchesTest();
	}
}
