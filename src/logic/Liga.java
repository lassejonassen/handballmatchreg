package logic;

import java.util.ArrayList;

public class Liga {
	
	private String ligaName;
	private ArrayList<Team> teams = new ArrayList<>();
	
	public Liga (String ligaName) {
		this.ligaName = ligaName;
	}
	
	
	private void addTeam (Team team) {
		teams.add(team);
	}

	public String getLigaName() {
		return ligaName;
	}

	public void setLigaName(String ligaName) {
		this.ligaName = ligaName;
	}

}
