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
//	public void createLeague(String ligaName) {
//		dataLayer.createLeague(ligaName);
//	}
	
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

	
	
	
}

