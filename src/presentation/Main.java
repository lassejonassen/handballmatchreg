package presentation;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application 
{

	public static void main(String[] args) 
	{
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		Menu menu = new Menu();
		stage.setTitle("Midtvejs projekt");
		menu.showMenu(stage);
		
	}


}
