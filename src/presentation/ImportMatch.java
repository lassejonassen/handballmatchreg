package presentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import data.Match;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.MatchImpl;

public class ImportMatch {
	private Label homeTeamName = new Label();
	private Label homeScored = new Label();
	private Label awayTeamName = new Label();
	private Label awayScored = new Label();
	private Label vsIndicator = new Label(" - ");

	Button chooseFileBtn = new Button("Choose file");
	TextField filePath = new TextField();

	private int gameSeconds;
	private int gameMinutes;

	private GridPane scrollingGrid = new GridPane();
	private ScrollPane sp = new ScrollPane(scrollingGrid);

	private Scene scene;
	private Stage window = new Stage();

	private ChildLayout childLayout = new ChildLayout();
	
	MatchImpl matchImpl = new MatchImpl();

	public ImportMatch() {
		if (fileChooser())
			showMatchReport();
		else // And a warning
			window.close();
	}

	@SuppressWarnings("static-access")
	private void showMatchReport() {
		childLayout.childTop.add(homeTeamName, 0, 0);
		childLayout.childTop.add(vsIndicator, 1, 0);
		childLayout.childTop.add(awayTeamName, 2, 0);
		childLayout.childTop.add(homeScored, 0, 1);
		childLayout.childTop.add(awayScored, 2, 1);
		childLayout.childBottom.getChildren().addAll(chooseFileBtn, filePath);
		GridPane.setHalignment(homeTeamName, HPos.CENTER);
		GridPane.setHalignment(vsIndicator, HPos.CENTER);
		GridPane.setHalignment(awayTeamName, HPos.CENTER);
		GridPane.setHalignment(homeScored, HPos.CENTER);
		GridPane.setHalignment(awayScored, HPos.CENTER);

		childLayout.childCenter.add(sp, 0, 0);
		childLayout.childCenter.setHgrow(sp, Priority.ALWAYS);
		sp.setId("sp");
		sp.setFitToWidth(true);
		scrollingGrid.setId("scrollingGrid");

		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Kamp rapport");
		window.show();
	}
	
	private boolean fileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Vælg kamprapport fil");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
		File selectedFile = fileChooser.showOpenDialog(window);
		
		if (selectedFile != null) {
			List<String[]> reportDataList = openCSVFile(selectedFile);
			int matchId = 0;
			
			// Getting the matchId
			for (int i = 0; i < 1; i++) {
				String matchIdStr = reportDataList.get(1)[6];
				matchId = Integer.parseInt(matchIdStr.stripLeading());
			}
			if (matchImpl.matchExists(matchId)) {
				matchExistsWarning();
				return false;
			} else {
				int leagueId = 0;
				int team1Id = 0;
				int team2Id = 0;
				
				// Creating the match on the database
				for (int i = 0; i < 1; i++) {
					String leagueIdStr = reportDataList.get(1)[7];
					leagueId = Integer.parseInt(leagueIdStr.stripLeading());
					String team1IdStr = reportDataList.get(1)[2];
					team1Id = Integer.parseInt(team1IdStr.stripLeading());
					String team2IdStr = reportDataList.get(1)[5];
					team2Id = Integer.parseInt(team2IdStr.stripLeading());
				}
				matchImpl.createMatch(leagueId, team1Id, team2Id);
				Match match = matchImpl.latestInsert();
				matchImpl.matchPlayed(match);
				
				// Getting the data, ID, timestamp, teamid
				for (int i = 3; i < 4; i++) {
					
				}
				
				return true;
			}
		} else {
			return false;
		}
	}
	
	
	private List<String[]> openCSVFile(File file) {
		List<String[]> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				list.add(line.split(","));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void matchExistsWarning() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Match WARNING");
		alert.setHeaderText("The match already exists");
		alert.setContentText("Du kan ikke importerer en rapport fra en kamp der er i systemet.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}
}
