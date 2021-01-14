package logic;


import data.DataLayer;
import data.Match;
import data.Team;

public class iMatchImpl implements iMatch {
	DataLayer dataLayer = new DataLayer();
	
	/**
	 * @author $ Lasse Jonassen
	 *
	 * @tags $ { Create match, create suspension }
	 */
	public void createMatch(Team team, Team team2) {
		dataLayer.createMatch(team.getId(), team2.getId());
	}
	
	public void createSuspension(Match match, Team team, String time) {
		dataLayer.createSuspension(match.getId(), team.getId(), time);
	}
	
	/**
	 * @author $ Lucas Elley
	 *
	 * @tags $ { Delete match, Update match }
	 */
	
	public void deleteMatch(Team team, Team team2) {
		dataLayer.deleteMatch(team.getId(), team2.getId());
	}
	
	public void updateMatch(Team team, Team team2) {
		dataLayer.updateMatch(team.getId(), team2.getId());
	}

}
