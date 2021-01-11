package presentation;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Layout 
/*Author: Calle 
 *Date: 11-01-2021
*/
{
	protected BorderPane bPane = new BorderPane();
	protected StackPane stackPane = new StackPane();
	protected VBox top = new VBox();
	protected VBox bottom = new VBox();
	protected VBox center = new VBox();
	protected HBox left = new HBox();
	protected HBox right = new HBox();
	
	public Layout() 
	{
		topLayout();
		bottomLayout();
		centerLayout();
		leftLayout();
		rightLayout();
	}
	
	private void topLayout()
	{
		bPane.setTop(top);
		top.setId("top");
		
		Label heading = new Label();
		top.getChildren().add(heading);
		heading.setText("Midtvejs Projekt test tekst!");
	}
	
	private void bottomLayout()
	{
		bPane.setBottom(bottom);
		bottom.setId("bottom");
		
	}
	
	private void centerLayout()
	{
		bPane.setCenter(center);
		center.setId("center");
	}
	
	private void leftLayout()
	{
		bPane.setLeft(stackPane);
		left.setId("left");
		stackPane.getChildren().add(left);
		StackPane.setAlignment(left, Pos.CENTER);
		
	}
	
	private void rightLayout()
	{
		bPane.setRight(right);
		right.setId("right");
		
	}

}
