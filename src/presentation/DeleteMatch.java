package presentation;

import data.League;
import data.Match;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.LeagueImpl;
import logic.MatchImpl;

public class DeleteMatch {
	
	/*
	 * Author: Lucas Elley
	 * Date: 15/01/2021
	 */
	private Button deleteMatchBtn = new Button ("Slet Kamp");
	
	private ComboBox<Match> matchChoice = new ComboBox<Match>();
	private ComboBox<League> ligaChoice = new ComboBox<League>();
	
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
		MatchImpl matchImpl = new MatchImpl();
		
		ligaChoice.getItems().addAll(leagueImpl.getAllLeagues());
		ligaChoice.setOnAction(e -> {
			matchChoice.getItems().addAll(matchImpl.getMatchesByLeagueID(ligaChoice.getSelectionModel().getSelectedItem()));
		});
	}
	
	private void btnFunctionality() {
		MatchImpl matchImpl = new MatchImpl();
		deleteMatchBtn.setOnAction(e -> {
			if(!(matchChoice.getSelectionModel().getSelectedItem() == null))
			{
				matchImpl.deleteMatch(matchChoice.getSelectionModel().getSelectedItem());
				window.close();
			}	
		});
	}
}
