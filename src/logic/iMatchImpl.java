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

}
