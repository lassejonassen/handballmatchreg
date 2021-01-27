package presentation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import data.Match;
import data.ReportDTO;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MatchReport {
	private Button closeBtn = new Button("Luk");
	private Button printReport = new Button("Print");

	private Label homeTeamName = new Label();
	private Label homeScored = new Label();
	private Label awayTeamName = new Label();
	private Label awayScored = new Label();
	private Label vsIndicator = new Label(" - ");

	private int gameSeconds;
	private int gameMinutes;
	private int homeGoals = 0;
	private int awayGoals = 0;
	private GridPane scrollingGrid = new GridPane();
	private ScrollPane sp = new ScrollPane(scrollingGrid);

	private Scene scene;
	private Stage window = new Stage();

	private ChildLayout childLayout = new ChildLayout();

	public MatchReport(Match match, ArrayList<ReportDTO> eventList) 
	{
		showMatchReport(match);
		matchReportBtnFunctionality(match, eventList);
		insertEvents(match, eventList);
	}

	@SuppressWarnings("static-access")
	private void showMatchReport(Match match) 
	{
		childLayout.childBottom.getChildren().addAll(printReport, closeBtn);
		childLayout.childTop.add(homeTeamName, 0, 0);
		childLayout.childTop.add(vsIndicator, 1, 0);
		childLayout.childTop.add(awayTeamName, 2, 0);
		childLayout.childTop.add(homeScored, 0, 1);
		childLayout.childTop.add(awayScored, 2, 1);
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

		homeTeamName.setText("" + match.getTeam1Name());
		awayTeamName.setText("" + match.getTeam2Name());
		homeScored.setText("" + match.getTeam1Goals());
		awayScored.setText("" + match.getTeam2Goals());

		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Kamp rapport");
		window.show();
	}

	private void insertEvents(Match match, ArrayList<ReportDTO> eventList) 
	{
		String matchEvent;
		for (int i = 0; i < eventList.size(); i++) 
		{
//			scrollingGrid.add(new Label(homeGoals + " - " + awayGoals), 1, i);
			if (eventList.get(i).getGoal() != null) 
			{
				gameSeconds = eventList.get(i).getGoal().getTimeStamp();
				gameMinutes = 0;
				for(int k = 0;k<eventList.get(i).getGoal().getTimeStamp();k++)
				{
					if (gameSeconds >= 60) 
					{
						gameSeconds = gameSeconds - 60;
						gameMinutes++;
						System.out.println("maal" + gameMinutes + " " + gameSeconds);
					}
				}
				matchEvent = " GOAL! ";
				if (eventList.get(i).getGoal().getTeamId() == match.getTeam1Id()) {
					homeGoals++;
					scrollingGrid.add(
							new Label(gameMinutes + ":" + gameSeconds + " - " + match.getTeam1Name() + matchEvent), 0,
							i);
				} else {
					awayGoals++;
					scrollingGrid.add(
							new Label(matchEvent + match.getTeam2Name() + " - " + gameMinutes + ":" + gameSeconds), 2,
							i);
					
				}
				scrollingGrid.add(new Label(homeGoals + " - " + awayGoals), 1, i);
			} else 
			{
				gameSeconds = eventList.get(i).getSuspension().getMatchTime();
				gameMinutes = 0;
				for(int k = 0;k<eventList.get(i).getSuspension().getMatchTime();k++)
				{
					if (gameSeconds >= 60) 
					{
						gameSeconds = gameSeconds - 60;
						gameMinutes++;
						System.out.println("suspension" + gameMinutes + " " + gameSeconds);
					}
				}
				matchEvent = " Udvisning! ";
				if (eventList.get(i).getSuspension().getTeamId() == match.getTeam1Id()) {
					scrollingGrid.add(
							new Label(gameMinutes + ":" + gameSeconds + " - " + match.getTeam1Name() + matchEvent), 0,
							i);
				} else {
					scrollingGrid.add(
							new Label(matchEvent + match.getTeam2Name() + " - " + gameMinutes + ":" + gameSeconds), 2,
							i);
				}
				scrollingGrid.add(new Label(homeGoals + " - " + awayGoals), 1, i);
			}
		}

	}

	private void matchReportBtnFunctionality(Match match, ArrayList<ReportDTO> eventList) {
		printReport.setOnAction(e -> exportReport(eventList, match));
		closeBtn.setOnAction(e -> window.close());
	}

	private void exportReport(ArrayList<ReportDTO> eventList, Match match) {
		DirectoryChooser dirChooser = new DirectoryChooser();
		File selectedDir = dirChooser.showDialog(window);

		try {
			LocalDate date = LocalDate.now();
			FileWriter writer = new FileWriter(selectedDir + "/"+ match.getTeam1Name() + " - " + match.getTeam2Name() + " kamprapport " + date + ".csv");
			writer.append("Hold navn 1");
			writer.append(", ");
			writer.append("Hold 1 maal");
			writer.append(", ");
			writer.append("Hold 1 ID");
			writer.append(", ");
			writer.append("Hold navn 2");
			writer.append(", ");
			writer.append("Hold 2 maal");
			writer.append(", ");
			writer.append("Hold 2 ID");
			writer.append(", ");
			writer.append("Kamp ID");
			writer.append(", ");
			writer.append("Liga ID");
			writer.append('\n');
			writer.append(match.getTeam1Name());
			writer.append(", ");
			writer.append(String.valueOf(match.getTeam1Goals()));
			writer.append(", ");
			writer.append(String.valueOf(match.getTeam1Id()));
			writer.append(", ");
			writer.append(match.getTeam2Name());
			writer.append(", ");
			writer.append(String.valueOf(match.getTeam2Goals()));
			writer.append(", ");
			writer.append(String.valueOf(match.getTeam2Id()));
			writer.append(", ");
			writer.append(String.valueOf(match.getMatchID()));
			writer.append(", ");
			writer.append(String.valueOf(match.getLeagueId()));
			writer.append('\n');
			writer.append("Type");
			writer.append(", ");
			writer.append("Id");
			writer.append(", ");
			writer.append("matchId");
			writer.append(", ");
			writer.append("timeStamp");
			writer.append(", ");
			writer.append("teamId");
			writer.append('\n');
			StringBuilder sb = new StringBuilder();
			for (ReportDTO event : eventList) {
				if (event.getSuspension() != null)
					sb.append(event.suspensionString());
				else if (event.getGoal() != null)
					sb.append(event.goalString());
				sb.append('\n');
			}
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}