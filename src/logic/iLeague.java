package logic;

import java.util.ArrayList;
import java.util.Optional;

import data.League;

public interface iLeague {
	public void createLeague(Optional<String> leagueName);
	public ArrayList<League> getAllLeagues();
	public boolean deleteLeague(Optional<League> league);
	
}
