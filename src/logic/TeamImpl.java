package logic;
import data.DataLayer;

public class TeamImpl {

	DataLayer dataLayer = new DataLayer();
	
	public void getAllTeams() {
		dataLayer.getAllTeams();
		
	}
}
