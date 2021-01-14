package logic;

import java.util.ArrayList;
import java.util.Optional;
import data.DataLayer;
import data.League;

public class LeagueImpl implements iLeague {
	DataLayer dataLayer = new DataLayer();
	
	/*
	 * Author: Lucas Elley
	 * 12/01/2021 
	 */
	
	public void createLeague(League league) {
		dataLayer.createLeague(league.getName());
	}
	
	/**
	 * @author $ Lasse Jonassen
	 *
	 * @tags $ { Read, update, delete }
	 */
	public ArrayList<League> getAllLeagues() {
		return dataLayer.getAllLeagues();
	}
	
	public boolean updateLeague(League league, String newName) {
		dataLayer.updateLeague(league.getId(), newName);
		return true;
	}
	
	public boolean deleteLeague(League league) {
		return true;
	}
	
	

	
	
	
}

