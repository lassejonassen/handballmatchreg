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
	
	public boolean updateTeam(Team team, String newName, League league) {
		dataLayer.updateTeam(team.getId(), newName, league.getId());
		return true;
	}
	
	public ArrayList<Team> getAllTeams(int league_id) {
		return dataLayer.getAllTeams(league_id);
	}
	
	public void deleteTeam(Team team) {
		dataLayer.deleteTeam(team.getId());
	}
}
