package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MatchReport 
{
	private Button closeBtn = new Button("Luk");
	private Button printReport = new Button("Print");
	
	private Scene scene;
	private Stage window = new Stage();
	
	private ChildLayout childLayout = new ChildLayout();
	
	public MatchReport()
	{
		showMatchReport();
		matchReportBtnFunctionality();
	}
	
	private void showMatchReport()
	{
		childLayout.childBottom.getChildren().addAll(printReport, closeBtn);
		
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
