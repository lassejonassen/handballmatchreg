package logic;

import data.Match;

public interface iSuspension {
	public void create(Match match, int teamId, int timeStamp);
	public void delete(Match match, int teamId, int suspensionId);
}
