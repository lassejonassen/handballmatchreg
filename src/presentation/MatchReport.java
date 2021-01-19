package presentation;

import data.Match;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MatchReport 
{
	private Button closeBtn = new Button("Luk");
	private Button printReport = new Button("Print");
	
	private Label homeTeamName = new Label();
	private Label homeScored = new Label();
	private Label awayTeamName = new Label();
	private Label awayScored = new Label();
	private Label vsIndicator = new Label("-");
	
	private Scene scene;
	private Stage window = new Stage();
	
	private ChildLayout childLayout = new ChildLayout();
	
	public MatchReport(Match match)
	{
		showMatchReport(match);
		matchReportBtnFunctionality();
	}
	
	private void showMatchReport(Match match)
	{
		childLayout.childBottom.getChildren().addAll(printReport, closeBtn);
		childLayout.childCenter.add(homeTeamName, 0, 0);
		childLayout.childCenter.add(vsIndicator, 1, 0);
		childLayout.childCenter.add(awayTeamName, 2, 0);
		childLayout.childCenter.add(homeScored, 0, 1);
		childLayout.childCenter.add(awayScored, 2, 1);
		homeTeamName.setStyle("-fx-font-size: 4em;");
		awayTeamName.setStyle("-fx-font-size: 4em;");
		homeScored.setStyle("-fx-font-size: 4em;");
		awayScored.setStyle("-fx-font-size: 4em;");
		
		homeTeamName.setText("" + match.getTeam1Id());
		awayTeamName.setText("" + match.getTeam2Id());
		homeScored.setText("" + match.getTeam1Goals());
		awayScored.setText("" + match.getTeam2Goals());
		
		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Kamp rapport");
		window.show();
	}
	
	private void matchReportBtnFunctionality()
	{
//		printReport.setOnAction(e ->);
		closeBtn.setOnAction(e -> window.close());
	}

}
