package logic;
import data.League;
import data.Team;
import java.util.ArrayList;

public interface TeamInterface {
	public void createTeam(League league, String name);
	public ArrayList<Team> getAllTeams(int league_id);
}
