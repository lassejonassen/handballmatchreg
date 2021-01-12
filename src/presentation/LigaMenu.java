package presentation;

import java.util.ArrayList;
import java.util.Arrays;

import data.Team;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.TeamImpl;

public class LigaMenu 
{	
	private Button createLigaBtn = new Button("Opret Liga");
	private Button updateLigaBtn = new Button("Opdater Liga");
	private Button deleteLigaBtn = new Button("Slet Liga");
	
	private Button backBtn = new Button("Tilbage");
	
	private Button createTeamBtn = new Button("Opret hold");
	private Button updateTeamBtn = new Button("Opdatere hold");
	
	private Layout layout = new Layout();
	
	public void ligaShowMenu(Stage stage)
	{
		layout.left.getChildren().addAll(createLigaBtn, updateLigaBtn, deleteLigaBtn, backBtn);
		layout.bottom.getChildren().addAll(updateTeamBtn, createTeamBtn);
		
		layout.left.setTopAnchor(createLigaBtn, 0.0);
		layout.left.setTopAnchor(updateLigaBtn, 50.0);
		layout.left.setTopAnchor(deleteLigaBtn, 100.0);
		layout.left.setBottomAnchor(backBtn, 0.0);
		
		ligaButtonFunctionality(stage);
		new LeagueTableView(layout);
		
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	private void ligaButtonFunctionality(Stage stage)
	{
		UpdateHold updateHold = new UpdateHold();
		Menu menu = new Menu();
//		createLigaBtn.setOnAction(e -> insert thingy here);
//		updateLigaBtn.setOnAction(e -> insert thingy here);
//		deleteLigaBtn.setOnAction(e -> insert thingy here);
		backBtn.setOnAction(e -> menu.showMenu(stage));
		updateTeamBtn.setOnAction(e -> updateHold.showHoldUpdate());
//		createTeamBtn.setOnAction(e -> holdMenu.showCreateTeamWindow());
	}

}
