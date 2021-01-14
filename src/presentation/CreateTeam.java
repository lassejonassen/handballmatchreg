package presentation;

import data.League;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.LeagueImpl;
import logic.TeamImpl;

public class CreateTeam 
{
	private Stage window = new Stage();
	private Scene scene;
	
	private TextField teamName = new TextField();
	private ComboBox<League> leagueChoice = new ComboBox();
	
	private Button confirmCreateBtn = new Button("Opret");
	private Button cancelCreateBtn = new Button("Annullere");
	
	private ChildLayout childLayout = new ChildLayout();
	private LeagueImpl leagueImpl = new LeagueImpl();
	private Validation validation = new Validation();
	private TeamImpl teamImpl = new TeamImpl();
	
	public CreateTeam()
	{
		showTeamCreate();
		createTeamButtonFunctionality();
		
	}

	private void showTeamCreate()
	{ 
		leagueChoice.getItems().addAll(leagueImpl.getAllLeagues());
		
		childLayout.childTop.getChildren().add(new Label("Opret hold"));
		childLayout.childBottom.getChildren().addAll(confirmCreateBtn, cancelCreateBtn);
		
		childLayout.childCenter.add(new Label("Hold navn"), 0, 0);
		childLayout.childCenter.add(teamName, 1, 0);
		childLayout.childCenter.add(new Label("Vælg liga"), 0, 1);
		childLayout.childCenter.add(leagueChoice, 1, 1);
		
		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Opret hold");
		window.show();
	}
	
	private void createTeamButtonFunctionality()
	{
		confirmCreateBtn.setOnAction(e -> createConfirm());
		cancelCreateBtn.setOnAction(e -> window.close());
	}
	
	private void createConfirm()
	{
		if(validation.emptyStringWarning(teamName.getText()))
			if(validation.confirmChanges())
			{
				teamImpl.createTeam(leagueChoice.getSelectionModel().getSelectedItem(), teamName.getText());
				window.close();
			}
	}
	
}
