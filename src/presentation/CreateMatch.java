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
import logic.iMatchImpl;

public class CreateMatch {

	private Button createMatchBtn = new Button("Opret Kamp");
	private Button cancelMatchBtn = new Button("Annuller Kamp");
	private int id = 0;
	private ComboBox<Team> homeChoice = new ComboBox();
	private ComboBox<Team> awayChoice = new ComboBox();
	private ComboBox<League> ligaChoice = new ComboBox();
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
		Scene scene = new Scene(teamLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Tilføj kamp");
		window.show();
	}

	private void matchCreateCenter() {
		// ComboBox kalde på hold liste.

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

	private void btnFunctionality() {
		iMatchImpl matchImpl = new iMatchImpl();
		createMatchBtn.setOnAction(e -> {
			matchImpl.createMatch(homeChoice.getSelectionModel().getSelectedItem(),
					awayChoice.getSelectionModel().getSelectedItem(), id);
			window.close();

			}
		);
	}
}
