package logic;

import data.DataLayer;
import data.Match;

public class SuspensionImpl implements iSuspension {
	DataLayer dataLayer = new DataLayer();
	
	public void create(Match match, int teamId, int timeStamp) {
		dataLayer.createSuspension(match.getMatchID(), teamId, timeStamp);
	}
	
	public void create(int matchId, int teamId, int timeStamp) {
		dataLayer.createSuspension(matchId, teamId, timeStamp);
	}

	public void delete(Match match, int teamId) {
		dataLayer.deleteSuspension(match.getMatchID(), teamId);
	}
	
	
	

}
