package logic;


import data.DataLayer;

public class iMatchImpl implements iMatch {
	
	DataLayer dataLayer = new DataLayer();
	
	// Author: Lasse Jonassen
	// Created: 2020_01_11
	public void createMatch(Team team, Team team2) {
		dataLayer.createMatch(team.getId(), team2.getId());
	}

}
