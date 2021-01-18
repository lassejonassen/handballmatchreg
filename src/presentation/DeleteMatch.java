package presentation;

import data.League;
import data.Match;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.LeagueImpl;
import logic.iMatchImpl;

public class DeleteMatch {
	
	/*
	 * Author: Lucas Elley
	 * Date: 15/01/2021
	 */
	private int id = 0;
	private Button deleteMatchBtn = new Button ("Slet Kamp");
	
	private ComboBox<Match> matchChoice = new ComboBox();
	private ComboBox<League> ligaChoice = new ComboBox();
	
	private Stage window = new Stage();
	private ChildLayout teamLayout = new ChildLayout();
	
	
	public DeleteMatch() {
		showMatchDelete();
		matchDeleteCenter();
		comboBoxData();
		btnFunctionality();
	}
	
	protected void showMatchDelete() {
		Scene scene = new Scene(teamLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Slet Kamp");
		window.show();
	}
	
	private void matchDeleteCenter() {
		teamLayout.childBottom.getChildren().addAll(deleteMatchBtn);
		teamLayout.childCenter.add(new Label("Valg af liga"), 0, 0);
		teamLayout.childCenter.add(ligaChoice, 2, 0);
		teamLayout.childCenter.add(new Label("Valg af kamp"), 0, 1);
		teamLayout.childCenter.add(matchChoice, 2, 1);
	}
	
	private void comboBoxData() {
		LeagueImpl leagueImpl = new LeagueImpl();
		iMatchImpl matchImpl = new iMatchImpl();
		
		ligaChoice.getItems().addAll(leagueImpl.getAllLeagues());
		ligaChoice.setOnAction(e -> {
			id = ligaChoice.getSelectionModel().getSelectedItem().getId();
			matchChoice.getItems().addAll(matchImpl.getAllMatches(id));
		});
	}
	
	private void btnFunctionality() {
		iMatchImpl matchImpl = new iMatchImpl();
		deleteMatchBtn.setOnAction(e -> {
			matchImpl.deleteMatch(matchChoice.getSelectionModel().getSelectedItem());
		window.close();
		} 
	);
}
	
	
}
