package logic;
import java.util.ArrayList;

import data.DataLayer;
import data.Team;


public class TeamImpl {
	DataLayer dataLayer = new DataLayer();
	
	
	public void createTeam(Liga liga, String name) {
		dataLayer.createTeam(new Team(name).getName(), liga.getligaId());
	}
	
	public ArrayList<Team> getAllTeams() {
		ArrayList<Team> teams = dataLayer.getAllTeams(1);
		return teams;
	}
}
