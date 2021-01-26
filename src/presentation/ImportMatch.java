package presentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import data.Goal;
import data.Match;
import data.ReportDTO;
import data.Suspension;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.ReportDTOImpl;

public class ImportMatch 
{
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
	
	public ImportMatch()
	{
		showMatchReport();
//		insertEvents();
	}
	
	@SuppressWarnings("static-access")
	private void showMatchReport()
	{
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
		
//		homeTeamName.setText("" + match.getTeam1Name());
//		awayTeamName.setText("" + match.getTeam2Name());
//		homeScored.setText("" + match.getTeam1Goals());
//		awayScored.setText("" + match.getTeam2Goals());
		
		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Kamp rapport");
		window.show();
	}
//	private void insertEvents()
//	{
//		ArrayList<String> eventList = MatchInfoFromCSV();
//		String matchEvent;
//		for(int i = 0; i < eventList.size(); i++)
//		{
//			scrollingGrid.add(new Label(homeGoals + " - " + awayGoals), 1, i);
//			if(eventList.get(i).equals("Goal"))
//			{
//				matchEvent = " GOAL! ";
//				if(eventList.get(i).getGoal().getTeamId() == match.getTeam1Id())
//				{
//					scrollingGrid.add(new Label(gameMinutes + ":" + gameSeconds + " - " + match.getTeam1Name() + matchEvent), 0, i);
//					homeGoals++;
//				}else
//				{
//					scrollingGrid.add(new Label(matchEvent + match.getTeam2Name() + " - " + gameMinutes + ":" + gameSeconds), 2, i);
//					awayGoals++;
//				}
//			}else 
//			{
//				matchEvent = " Udvisning! ";
//				if(eventList.get(i).getSuspension().getTeamId() == match.getTeam1Id())
//				{
//					scrollingGrid.add(new Label(gameMinutes + ":" + gameSeconds + " - " + match.getTeam1Name() + matchEvent), 0, i);
//				}else
//				{
//					scrollingGrid.add(new Label(matchEvent + match.getTeam2Name() + " - " + gameMinutes + ":" + gameSeconds), 2, i);
//				}
//			}
//		}
//	}
	
	private ArrayList<String> MatchInfoFromCSV()
	{
		ArrayList<String> stringList = new ArrayList<String>();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Vælg kamprapport fil");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
		File selectedFile = fileChooser.showOpenDialog(window);
		
		if(selectedFile != null)
		{
			List<String[]> tempList = openCSVFile(selectedFile);
			
			for(String[] stringEvent : tempList)
			{
				StringBuilder sb = new StringBuilder();
				String[] event = stringEvent;
				sb.append(event[0]); //Type
				sb.append(event[2]); //matchID
				int parse = Integer.parseInt(event[3]);
				gameMinutes = 0;
				gameSeconds = parse;
				while(parse >= 60)
				{
					gameSeconds = parse - 60;
					gameMinutes++;
				}
				String timeString = gameMinutes + ":" + gameSeconds;
				sb.append(timeString); //timeStamp
				sb.append(event[4]); //teamId
				System.out.println(sb.toString());
				stringList.add(sb.toString());
			}
		}
		return stringList;
	}
	
	private List<String[]> openCSVFile(File file)
	{
		List<String[]> list = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String line = "";
			while ((line = br.readLine()) != null)
			{
				list.add(line.split(","));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
