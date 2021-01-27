package presentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import data.Match;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.GoalImpl;
import logic.MatchImpl;
import logic.ReportDTOImpl;
import logic.SuspensionImpl;

public class ImportMatch {
	private Stage window = new Stage();
	private MatchImpl matchImpl = new MatchImpl();
	private GoalImpl goalImpl = new GoalImpl();
	private SuspensionImpl susImpl = new SuspensionImpl();
	private ReportDTOImpl reportDTOImpl = new ReportDTOImpl();
	private int lines = 0;

	public ImportMatch() {
		if (fileChooser());
		else
			window.close();
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
				match.setTeam1Name(matchImpl.getOneTeam(match.getTeam1Id()));
				match.setTeam2Name(matchImpl.getOneTeam(match.getTeam2Id()));
				
				int timeStamp = 0;
				int teamId = 0;
				int team1Goals = 0;
				int team2Goals = 0;
				// Getting the data, ID, timestamp, teamid
				for (int i = 3; i < lines; i++) {
					String type = reportDataList.get(i)[0];
					String timeStampStr = reportDataList.get(i)[3];
					timeStamp = Integer.parseInt(timeStampStr.stripLeading());
					String teamIdStr = reportDataList.get(i)[4];
					teamId = Integer.parseInt(teamIdStr.stripLeading());
					
					if (type.equals("Goal")) {
						goalImpl.create(match.getMatchID(), teamId, timeStamp);
						if (teamId == match.getTeam1Id())
							team1Goals++;
						else
							team2Goals++;
					}
					else if (type.equals("Suspension"))
						susImpl.create(match.getMatchID(), teamId, timeStamp);
					else
						return false;
				}
				
				match.setTeam1Goals(team1Goals);
				match.setTeam2Goals(team2Goals);
				matchImpl.updateMatchData(match);
				new MatchReport(match,reportDTOImpl.read(match));
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
				lines++;
				System.out.println(lines);
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
