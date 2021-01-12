package logic;
import java.util.ArrayList;

import data.DataLayer;
import data.League;
import data.Team;

public class LeagueImpl implements iLeague {
	DataLayer dataLayer = new DataLayer();
	
	/*
	 * Author: Lucas Elley
	 * 12/01/2021 
	 */
	public void createLiga(String ligaName) {
		dataLayer.createLiga(ligaName);
	}

	// Author: Lasse Jonassen
	// Created: 12-01-2021
	public ArrayList<League> getAllLeagues() {
		ArrayList<League> leagues = dataLayer.getAllLeagues();
		return leagues;
	}

	
	
	
}

