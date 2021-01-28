package presentation;

import data.League;
import data.Team;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.LeagueImpl;
import logic.TeamImpl;
import logic.MatchImpl;

public class CreateMatch {

	private Button createMatchBtn = new Button("Opret Kamp");
	private Button cancelMatchBtn = new Button("Annuller Kamp");
	private int id = 0;
	private ComboBox<Team> homeChoice = new ComboBox<Team>();
	private ComboBox<Team> awayChoice = new ComboBox<Team>();
	private ComboBox<League> ligaChoice = new ComboBox<League>();
	
	private Validation validation = new Validation();
	private MatchImpl matchImpl = new MatchImpl();
	private Stage window = new Stage();
	private Scene scene;
	private ChildLayout teamLayout = new ChildLayout();

	public CreateMatch() {
		showMatchCreate();
		matchCreateCenter();
		comboBoxData();
		btnFunctionality();
	}

	protected void showMatchCreate() {
		scene = new Scene(teamLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Tilf�j kamp");
		window.show();
	}

	private void matchCreateCenter() {
		// ComboBox kalde p� hold liste.

		teamLayout.childCenter.add(new Label("Valg af liga"), 0, 0);
		teamLayout.childCenter.add(ligaChoice, 2, 0);
		teamLayout.childCenter.add(new Label("Hjemme"), 0, 1);
		teamLayout.childCenter.add(homeChoice, 0, 2);
		teamLayout.childCenter.add(new Label(" - "), 1, 1);
		teamLayout.childCenter.add(new Label("Ude"), 2, 1);
		teamLayout.childCenter.add(awayChoice, 2, 2);
		teamLayout.childBottom.getChildren().addAll(cancelMatchBtn, createMatchBtn);

	}

	private void comboBoxData() {
		LeagueImpl leagueImpl = new LeagueImpl();
		TeamImpl teamImpl = new TeamImpl();

		ligaChoice.getItems().addAll(leagueImpl.getAllLeagues());
		ligaChoice.setOnAction(e -> {
			id = ligaChoice.getSelectionModel().getSelectedItem().getId();
			homeChoice.getItems().addAll(teamImpl.getAllTeams(id));
			awayChoice.getItems().addAll(teamImpl.getAllTeams(id));	
		});		
	}

	private void btnFunctionality() 
	{
		createMatchBtn.setOnAction(e -> createConfirm());
		cancelMatchBtn.setOnAction(e -> window.close());
	}
	
	private void createConfirm()
	{
		if(!(homeChoice.getSelectionModel().getSelectedItem() == null) & !(awayChoice.getSelectionModel().getSelectedItem() == null))
		{
			if(validation.confirmMatchCreate(homeChoice.getSelectionModel().getSelectedItem(), awayChoice.getSelectionModel().getSelectedItem()))
			{
				matchImpl.createMatch(homeChoice.getSelectionModel().getSelectedItem(),
						awayChoice.getSelectionModel().getSelectedItem(), id);
				window.close();
			}	
		}
	}
}
