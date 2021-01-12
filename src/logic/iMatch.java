package logic;

import data.Team;
public interface iMatch {
	
	// Author: Lasse Jonassen
	// Created: 12-01-2021
	public void createMatch(Team team, Team team2);
	public void createSuspension(Match match, Team team, String time);

}
