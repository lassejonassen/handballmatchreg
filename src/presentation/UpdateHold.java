package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UpdateHold 
{
	private Stage window = new Stage();
	private Scene scene;

	public void showHoldUpdate()
	{ 
		HoldLayout holdLayout = new HoldLayout();
		scene = new Scene(holdLayout.childRoot,400,400);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Opdater hold");
		window.show();
	}
}
