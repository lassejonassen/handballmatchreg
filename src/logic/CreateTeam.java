package logic;
import data.DataLayer;

public class CreateTeam {
	/* 
	 * Author: Lucas Elley
	 * Date: 12/01/2021
	 */	
	DataLayer dataLayer = new DataLayer();
	public void createTeam(Liga liga, String name) {
		dataLayer.createTeam(new Team(name).getName(), liga.getligaId());
	}
}
