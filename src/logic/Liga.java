package logic;

import java.util.ArrayList;

import data.Team;

public class Liga {
	/*
	 *  Author: Lucas Elley
	 *  Date: 12/01/2021
	 */
	private String ligaName;
	private int ligaId;
	private ArrayList<Team> teams = new ArrayList<>();
	
	public Liga (String ligaName) {
		this.ligaName = ligaName;
	}
	
	private void addTeam (Team team) {
		teams.add(team);
	}

	private String getLigaName() {
		return ligaName;
	}

	private void setLigaName(String ligaName) {
		this.ligaName = ligaName;
	}
	
	public int getligaId() {
		return ligaId;
	}
}
