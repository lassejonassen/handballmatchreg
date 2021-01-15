package logic;


import java.util.ArrayList;

import data.DataLayer;
import data.League;
import data.Match;
import data.Team;
import presentation.LigaMenu;

public class iMatchImpl implements iMatch {
	DataLayer dataLayer = new DataLayer();
	
	/**
	 * @author $ Lasse Jonassen
	 *
	 * @tags $ { Create match, create suspension }
	 */
	public void createMatch(Team team, Team team2, int leagueID) {
		dataLayer.createMatch(team.getId(), team2.getId(), leagueID);
	}
	
	public void createSuspension(Match match, Team team, String time) {
		dataLayer.createSuspension(match.getId(), team.getId(), time);
	}
	
	/**
	 * @author $ Lucas Elley
	 *
	 * @tags $ { Delete match, Update match }
	 */
	
	public void deleteMatch(Match match) {
		dataLayer.deleteMatch(match.getId());
	}
	
	public ArrayList<Match> getAllMatches(int leagueID) {
		return dataLayer.getAllMatches(leagueID);
	}
//	public void updateMatch(Match match) {
//		dataLayer.updateMatch(match.getId());
//	}
//
}
