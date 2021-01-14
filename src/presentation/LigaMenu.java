package presentation;

import logic.LeagueImpl;
import data.League;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LigaMenu {
	private Button createLeagueBtn = new Button("Opret Liga");
	private Button updateLeagueBtn = new Button("Opdater Liga");
	private Button deleteLigaBtn = new Button("Slet Liga");
	private ComboBox<League> leagueDropdown = new ComboBox<League>();

	private Button backBtn = new Button("Tilbage");

	private Button createTeamBtn = new Button("Opret hold");
	private Button updateTeamBtn = new Button("Opdatere hold");

	private Layout layout = new Layout();

	private Validation validate = new Validation();
	
	
	public LigaMenu(Stage stage) {
		ligaButtonFunctionality(stage);
		showLeagueMenu(stage);
		leagueDropDown();

	}

	private void showLeagueMenu(Stage stage) {
		layout.left.getChildren().addAll(createLeagueBtn, updateLeagueBtn, deleteLigaBtn, backBtn, leagueDropdown);
		layout.bottom.getChildren().addAll(updateTeamBtn, createTeamBtn);
		leagueDropdown.setId("leagueDropDown");

		layout.left.setTopAnchor(createLeagueBtn, 0.0);
		layout.left.setTopAnchor(updateLeagueBtn, 50.0);
		layout.left.setTopAnchor(deleteLigaBtn, 100.0);
		layout.left.setTopAnchor(leagueDropdown, 150.0);
		layout.left.setBottomAnchor(backBtn, 0.0);

		new LeagueTableView(layout);

		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	private void ligaButtonFunctionality(Stage stage) {
		Menu menu = new Menu();
		createLeagueBtn.setOnAction(e -> createLeague());
		updateLeagueBtn.setOnAction(e -> updateLeague());
		deleteLigaBtn.setOnAction(e -> deleteLeague());
		backBtn.setOnAction(e -> menu.showMenu(stage));
		updateTeamBtn.setOnAction(e -> new CreateTeam());
//		createTeamBtn.setOnAction(e -> holdMenu.showCreateTeamWindow());
	}

	/**
	 * @author $ Lasse Jonassen
	 *
	 * @tags $ { Everything league regarded }
	 */
	private void leagueDropDown() {
		LeagueImpl leagueImpl = new LeagueImpl();
		leagueDropdown.getItems().addAll(leagueImpl.getAllLeagues());
	}

	private void createLeague() {
		LeagueImpl leagueImpl = new LeagueImpl();
		ChildLayout layout = new ChildLayout();
		Label header = new Label("Opret ny liga");
		Label guideLabel = new Label("Skriv venligst navnet p� den nye liga: ");
		TextField leagueNameField = new TextField();
		leagueNameField.setPromptText("Liga navn");
		Button addBtn = new Button("OK");
		Button cancelBtn = new Button("Annuller");
		Scene scene = new Scene(layout.childRoot, 600, 400);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		layout.childTop.getChildren().add(header);
		layout.childCenter.add(guideLabel, 0, 0);
		layout.childCenter.add(leagueNameField, 1, 0);
		layout.childCenter.add(addBtn, 0, 1);
		layout.childCenter.add(cancelBtn, 1, 1);
		addBtn.setOnAction(e -> {
			if (validate.emptyStringWarning(leagueNameField.getText()))
				if (validate.confirmChanges()) {
					leagueImpl.createLeague(new League(leagueNameField.getText()));
					stage.close();
				}
		});
		cancelBtn.setOnAction(e -> stage.close());
	}

	private void deleteLeague() {
		LeagueImpl leagueImpl = new LeagueImpl();
		ChildLayout layout = new ChildLayout();
		Label header = new Label("Slet en liga");
		layout.childTop.getChildren().add(header);
		Label guideLabel = new Label("V�lg venligst den liga du vil slette");
		layout.childCenter.add(guideLabel, 0, 0);
		ComboBox<League> leagues = new ComboBox<>();
		leagues.getItems().addAll(leagueImpl.getAllLeagues());
		layout.childCenter.add(leagues, 1, 0);
		Button addBtn = new Button("OK");
		Button cancelBtn = new Button("Annuller");
		layout.childCenter.add(addBtn, 0, 1);
		layout.childCenter.add(cancelBtn, 1, 1);
		Scene scene = new Scene(layout.childRoot, 600, 400);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		addBtn.setOnAction(e -> {
			if (validate.confirmChanges()) {
				leagueImpl.deleteLeague(leagues.getSelectionModel().getSelectedItem());
				stage.close();
			}
		});
		cancelBtn.setOnAction(e -> stage.close());
	}

	private void updateLeague() {
		LeagueImpl leagueImpl = new LeagueImpl();
		ChildLayout layout = new ChildLayout();
		Label header = new Label("Opdater en liga");
		layout.childTop.getChildren().add(header);
		Label guideLabel = new Label("V�lg den liga du vil opdaterer");
		layout.childCenter.add(guideLabel, 0, 0);
		ComboBox<League> leagues = new ComboBox<>();
		leagues.getItems().addAll(leagueImpl.getAllLeagues());
		layout.childCenter.add(leagues, 1, 0);
		Label guideLabel2 = new Label("Skriv ligaens nye navn");
		layout.childCenter.add(guideLabel2, 0, 1);
		TextField leagueNameField = new TextField();
		leagueNameField.setPromptText("Liga navn");
		layout.childCenter.add(leagueNameField, 1, 1);
		Button addBtn = new Button("OK");
		Button cancelBtn = new Button("Annuller");
		layout.childCenter.add(addBtn, 0, 2);
		layout.childCenter.add(cancelBtn, 1, 2);
		Scene scene = new Scene(layout.childRoot, 600, 400);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		addBtn.setOnAction(e -> {
			if (validate.confirmChanges()) {
				leagueImpl.updateLeague(leagues.getSelectionModel().getSelectedItem(), leagueNameField.getText());
				stage.close();
			}
		});
		cancelBtn.setOnAction(e -> stage.close());
	}

}
