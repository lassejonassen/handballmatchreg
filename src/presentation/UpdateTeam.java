package presentation;

import data.League;
import data.Team;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.LeagueImpl;
import logic.TeamImpl;

public class UpdateTeam 
{
	private Scene scene;
	private Stage window = new Stage();
	
	private TextField teamName = new TextField();
	private ComboBox<League> leagueChoice = new ComboBox();
	
	private Button confirmCreateBtn = new Button("Opret");
	private Button cancelCreateBtn = new Button("Annullere");
	
	private TextField loadedTeamName; 
	
	Team team;
	
	private ChildLayout childLayout = new ChildLayout();
	private LeagueImpl leagueImpl = new LeagueImpl();
	private Validation validation = new Validation();
	private TeamImpl teamImpl = new TeamImpl();
	
	public UpdateTeam()
	{
		showUpdateTeam();
//		team = new Team();
//		team = teamImpl.getAllTeams()
	}
	
	public void showUpdateTeam()
	{
		leagueChoice.getItems().addAll(leagueImpl.getAllLeagues());
		
		childLayout.childTop.getChildren().add(new Label("Opdater hold"));
		childLayout.childBottom.getChildren().addAll(confirmCreateBtn, cancelCreateBtn);
		
		childLayout.childCenter.add(loadedTeamName, 0, 0);
		
		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Opret hold");
		window.show();
	}
}
