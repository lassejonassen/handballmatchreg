package presentation;

import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class ChildLayout 
{
	protected BorderPane childRoot = new BorderPane();
	protected GridPane childTop = new GridPane();
	protected HBox childBottom = new HBox();
	protected GridPane childCenter = new GridPane();
	protected VBox childLeft = new VBox();
	protected VBox childRight = new VBox();
	
	public ChildLayout()
	{
		childTopLayout();
		childBottomLayout();
		childLeftLayout();
		childRightLayout();
		childCenterLayout();
		childRootId();
	}
	
	private void childRootId()
	{
		childRoot.setId("childRoot");
	}
	
	private void childTopLayout()
	{
		childRoot.setTop(childTop);
		childTop.setId("childTop");	
	}
	
	private void childBottomLayout()
	{
		childRoot.setBottom(childBottom);
		childBottom.setId("childBottom");
	}
	
	private void childCenterLayout() 
	{
		childRoot.setCenter(childCenter);
		childCenter.setId("childCenter");
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col2 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col3 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col4 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col5 = new ColumnConstraints();
		col1.setPercentWidth(20);
		
		childCenter.getColumnConstraints().addAll(col1, col2, col3, col4, col5);
	}
	
	private void childLeftLayout()
	{
		childRoot.setLeft(childLeft);
		childLeft.setId("childLeft");
	}
	
	private void childRightLayout()
	{
		childRoot.setRight(childRight);
		childRight.setId("childRight");
	}
}
