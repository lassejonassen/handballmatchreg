package presentation;

import javafx.application.Application;
import javafx.stage.Stage;

import data.DataLayer;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		DataLayer dataLayer = new DataLayer();
		dataLayer.createLiga("SuperLiga");
	}

}
