package logic;
import java.util.ArrayList;

import data.DataLayer;
import data.Team;


import data.League;

public class TeamImpl {
	DataLayer dataLayer = new DataLayer();
	
	
	public void createTeam(League league, String name) {
		dataLayer.createTeam(new Team(name).getName(), league.getId());
	}
	
	public ArrayList<Team> getAllTeams() {
		ArrayList<Team> teams = dataLayer.getAllTeams(1);
		return teams;
	}
}
