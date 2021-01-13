package logic;
import java.util.ArrayList;

import data.DataLayer;
import data.Team;


import data.League;

public class TeamImpl implements TeamInterface {
	DataLayer dataLayer = new DataLayer();
	
	public void createTeam(League league, String name) {
		dataLayer.createTeam(new Team(name).getName(), league.getId());
	}
	
	public ArrayList<Team> getAllTeams(League league) {
		return dataLayer.getAllTeams(league.getId());
	}
}
