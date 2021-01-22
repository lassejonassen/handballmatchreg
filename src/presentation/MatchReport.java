package presentation;

import java.util.ArrayList;

import data.Goal;
import data.League;
import data.Match;
import data.ReportDTO;
import data.Team;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class MatchReport 
{
	private Button closeBtn = new Button("Luk");
	private Button printReport = new Button("Print");
	
	private Label homeTeamName = new Label();
	private Label homeScored = new Label();
	private Label awayTeamName = new Label();
	private Label awayScored = new Label();
	private Label vsIndicator = new Label(" - ");
	
	private GridPane scrollingGrid = new GridPane();
	private ScrollPane sp = new ScrollPane(scrollingGrid);
	
	private Scene scene;
	private Stage window = new Stage();
	private int i = 0;
	
	private ChildLayout childLayout = new ChildLayout();
	
	public MatchReport(Match match, ArrayList<ReportDTO> eventList)
	{
		showMatchReport(match);
		matchReportBtnFunctionality(match);
		insertEvents(match, eventList);
	}
	
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
		for(int j = 0; j < eventList.size(); j++)
		{
			scrollingGrid.add(new Label(match.getTeam1Goals() + " - " + match.getTeam2Goals()), 1, i);
			if(eventList.get(i).getGoal() != null)
			{
				matchEvent = " GOAL! ";
				if(eventList.get(i).getGoal().getTeamId() == match.getTeam1Id())
				{
					scrollingGrid.add(new Label(eventList.get(i).getGoal().getTimeStamp() + " - " + match.getTeam1Name() + matchEvent), 0, i);
				}else
				{
					scrollingGrid.add(new Label(matchEvent + match.getTeam2Name() + " - " + eventList.get(i).getGoal().getTimeStamp()), 2, i);
				}
			}else 
			{
				matchEvent = " Udvisning! ";
				if(eventList.get(i).getSuspension().getTeamId() == match.getTeam1Id())
				{
					scrollingGrid.add(new Label(eventList.get(i).getSuspension().getMatchTime() + " - " + match.getTeam1Name() + matchEvent), 0, i);
				}else
				{
					scrollingGrid.add(new Label(matchEvent + match.getTeam2Name() + " - " + eventList.get(i).getSuspension().getMatchTime()), 2, i);
				}
			}
			i++;	
		}
	}
		
	private void matchReportBtnFunctionality(Match match)
	{
//		printReport.setOnAction(e -> testGridCellGet(match));
		closeBtn.setOnAction(e -> window.close());
	}

}
