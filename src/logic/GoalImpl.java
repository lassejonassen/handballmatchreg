package logic;

import data.DataLayer;
import data.Match;

public class GoalImpl implements iGoal {
	DataLayer dataLayer = new DataLayer();
	
	public void create(Match match, int teamId, int timeStamp) {
		dataLayer.createGoal(match.getMatchID(), timeStamp, teamId);
	}

}
