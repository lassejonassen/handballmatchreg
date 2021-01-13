package logic;
import java.util.ArrayList;
import java.util.Optional;

import com.sun.net.httpserver.Filter;

import data.DataLayer;
import data.League;

public class LeagueImpl implements iLeague {
	DataLayer dataLayer = new DataLayer();
	
	/*
	 * Author: Lucas Elley
	 * 12/01/2021 
	 */
	
	public void createLeague(Optional<String> leagueName) {
		String name = leagueName.map(String::toString).orElse("(empty)");
		dataLayer.createLeague(name);
	}
	

	// Author: Lasse Jonassen
	// Created: 12-01-2021
	public ArrayList<League> getAllLeagues() {
		ArrayList<League> leagues = dataLayer.getAllLeagues();
		return leagues;
	}
	
	// Author: Lasse Jonassen
	// Created: 13-01-2021
	@SuppressWarnings("static-access") // ofNullable
	public boolean deleteLeague(Optional<League> league) {
		league.ofNullable(new League());
		if (dataLayer.deleteLeague(league.get()))
			return true;
		else
			return false;		
	}
	
	

	
	
	
}

