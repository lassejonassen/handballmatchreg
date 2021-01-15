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
	
	private ComboBox<League> leagueChoice = new ComboBox();
	private ComboBox<Team> teamChoice = new ComboBox();
	
	private TextField teamName = new TextField();
	private ComboBox<League> updateTeamLeague = new ComboBox();
	
	private Button confirmUpdateBtn = new Button("Opdater");
	private Button cancelUpdateBtn = new Button("Annullere"); 
	
	private ChildLayout childLayout = new ChildLayout();
	private LeagueImpl leagueImpl = new LeagueImpl();
	private Validation validation = new Validation();
	private TeamImpl teamImpl = new TeamImpl();
	
	private int leagueId = 0;
	
	public UpdateTeam()
	{
		showUpdateTeam();
		updateTeamBtnFunctionality();
	}
	
	public void showUpdateTeam()
	{
		leagueChoice.getItems().addAll(leagueImpl.getAllLeagues());
		
		childLayout.childBottom.getChildren().addAll(confirmUpdateBtn, cancelUpdateBtn);
		
		childLayout.childCenter.add(new Label("Vælg liga"), 0, 0);
		childLayout.childCenter.add(leagueChoice, 1, 0);
		childLayout.childCenter.add(new Label("Vælg hold"), 0, 1);
		childLayout.childCenter.add(teamChoice, 1, 1);
		childLayout.childCenter.add(new Label("Vælg holdets nye navn"), 0, 2);
		childLayout.childCenter.add(teamName, 1, 2);
		
		
		
		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Opret hold");
		window.show();
	}
	
	private void teamAndLeagueChoice()
	{
		leagueId = leagueChoice.getSelectionModel().getSelectedItem().getId();
		teamChoice.getItems().clear();
		teamChoice.getItems().addAll(teamImpl.getAllTeams(leagueId));
	}
	
	private void updateTeamBtnFunctionality()
	{
		leagueChoice.setOnAction(e -> teamAndLeagueChoice());
		cancelUpdateBtn.setOnAction(e -> window.close());
		confirmUpdateBtn.setOnAction(e -> updateConfirm());
	}
	
	private void updateConfirm()
	{
		if(validation.emptyStringWarning(teamName.getText()))
			if(validation.confirmChanges())
			{
				teamImpl.updateTeam(teamChoice.getSelectionModel().getSelectedItem(), teamName.getText(), leagueChoice.getSelectionModel().getSelectedItem());
				window.close();
			}
	}
}
