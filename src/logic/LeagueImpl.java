package logic;

import java.util.ArrayList;
import data.DataLayer;
import data.League;

public class LeagueImpl implements iLeague {
	DataLayer dataLayer = new DataLayer();
	
	public void createLeague(League league) {
		dataLayer.createLeague(league.getName());
	}
	
	public ArrayList<League> getAllLeagues() {
		return dataLayer.getAllLeagues();
	}
	
	public League getLeagueById(int id)
	{
		return dataLayer.getLeagueById(id);
	}
	
	public boolean updateLeague(League league, String newName) {
		dataLayer.updateLeague(league.getId(), newName);
		return true;
	}
	
	public boolean deleteLeague(League league) {
		dataLayer.deleteLeague(league.getId());
		return true;
	}
}

