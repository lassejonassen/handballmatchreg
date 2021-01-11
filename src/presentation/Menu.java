package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu 
{
	private VBox root = new VBox();
	private Button btn1 = new Button("this is button 1");
	private Button btn2 = new Button("this is button 2");
	
	
	
	public void showMenu(Stage stage)
	{
		Layout layout = new Layout();
		root.getChildren().add(layout.bPane);
		layout.left.getChildren().addAll(btn1,btn2);
		btn1.setId("btn1");
		btn2.setId("btn2");
		
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}

}
