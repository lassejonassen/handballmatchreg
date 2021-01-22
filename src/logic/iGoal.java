package logic;

import data.Match;

public interface iGoal {
	public void create(Match match, int teamId, int timeStamp);
	public void delete(Match match, int teamId, int goalId);
}
