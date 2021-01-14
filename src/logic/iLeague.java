package logic;

import java.util.ArrayList;
import data.League;

public interface iLeague {
	public void createLeague(League league);
	public ArrayList<League> getAllLeagues();
	public boolean updateLeague(League league, String newName);
	public boolean deleteLeague(League league);
	
}
