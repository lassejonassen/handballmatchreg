package logic;

import java.util.ArrayList;

import data.League;

public interface iLeague {
	/*
	 *  Author: Lucas Elley
	 *  Date: 12/01/2021
	 */
	public void createLiga(String ligaName);
	
	// Author: Lasse Jonassen
	// Created: 12-01-2021
	public ArrayList<League> getAllLeagues();
	
}
