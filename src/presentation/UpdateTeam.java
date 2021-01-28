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
	
	private ComboBox<League> leagueChoice = new ComboBox<League>();
	private ComboBox<Team> teamChoice = new ComboBox<Team>();
	
	private TextField teamName = new TextField();
	private ComboBox<League> updateTeamLeague = new ComboBox<League>();
	
	private Button confirmUpdateBtn = new Button("Opdater");
	private Button cancelUpdateBtn = new Button("Annullere"); 
	
	private ChildLayout childLayout = new ChildLayout();
	private LeagueImpl leagueImpl = new LeagueImpl();
	private Validation validation = new Validation();
	private TeamImpl teamImpl = new TeamImpl();
	
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
		childLayout.childCenter.add(new Label("Vælg holdets liga"), 0, 3);
		childLayout.childCenter.add(updateTeamLeague, 1, 3);
		
		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Opdater hold");
		window.show();
	}
	
	private void teamAndLeagueChoice()
	{
		int leagueId = leagueChoice.getSelectionModel().getSelectedItem().getId();
		teamChoice.getItems().addAll(teamImpl.getAllTeams(leagueId));
	}
	
	private void teamChoiceSelection()
	{
		if (teamName.getText().isEmpty())
		{
			teamName.setText("");
		}
		else
		{
			teamName.setText(teamChoice.getSelectionModel().getSelectedItem().getName());
		}	
		updateTeamLeague.getItems().clear();
		updateTeamLeague.getItems().addAll(leagueImpl.getAllLeagues());
		updateTeamLeague.setValue(leagueImpl.getLeagueById(teamChoice.getSelectionModel().getSelectedItem().getLeagueId()));
	}
	
	private void resetTeamChoice()
	{
		teamName.setText("");
		updateTeamLeague.getItems().clear();
		updateTeamLeague.setValue(null);
		teamChoice.getSelectionModel().clearSelection();
		teamChoice.setValue(null);
		teamChoice.getItems().clear();
	}
	
	private void updateTeamBtnFunctionality()
	{
		leagueChoice.setOnMouseClicked(e -> resetTeamChoice());
		leagueChoice.setOnAction(e -> teamAndLeagueChoice());
		teamChoice.setOnAction(e -> teamChoiceSelection());
		cancelUpdateBtn.setOnAction(e -> window.close());
		confirmUpdateBtn.setOnAction(e -> updateConfirm());	
	}
	
	private void updateConfirm()
	{
		if(validation.emptyStringWarning(teamName.getText()))
			if(validation.confirmChanges())
			{
				teamImpl.updateTeam(teamChoice.getSelectionModel().getSelectedItem(), teamName.getText(), updateTeamLeague.getSelectionModel().getSelectedItem());
				window.close();
			}
	}
}
