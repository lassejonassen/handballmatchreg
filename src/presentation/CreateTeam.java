package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateTeam 
{
	private Stage window = new Stage();
	private Scene scene;
	
	private ChildLayout childLayout = new ChildLayout();
	
	public CreateTeam()
	{
		showHoldCreate();
	}

	public void showHoldCreate()
	{ 
		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Opret hold");
		window.show();
	}
}
