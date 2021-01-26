package presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Menu 
{
	private Button showLigaBtn = new Button("Liga");
	private Button showMatchBtn = new Button("Kamp");
	private Button closeBtn = new Button("Luk program");
	
	
	@SuppressWarnings("static-access")
	public Menu(Stage stage) {
		Layout layout = new Layout();
		layout.left.getChildren().addAll(showLigaBtn,showMatchBtn);
		layout.bottom.getChildren().addAll(closeBtn);
		layout.left.setTopAnchor(showLigaBtn, 0.0);
		layout.left.setTopAnchor(showMatchBtn, 50.0);
		

		buttonFunctionality(stage);
		String imageUrl = "file:///..\\archives\\hblogo.jpg";
		Image img = new Image(imageUrl);
		ImageView iView = new ImageView(img);
		
		layout.center.getChildren().add(iView);
		
		iView.setFitHeight(400);
		iView.setFitWidth(650);
		iView.setPreserveRatio(false);
		
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void buttonFunctionality(Stage stage)
	{
		showMatchBtn.setOnAction(e -> new MatchMenu(stage));
		showLigaBtn.setOnAction(e -> new LigaMenu(stage));
		closeBtn.setOnAction(e -> System.exit(0));
  }
}
