package presentation;

import javafx.geometry.HPos;
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
		childBottom.setAlignment(Pos.CENTER_RIGHT);
	}
	
	private void childCenterLayout() 
	{
		childRoot.setCenter(childCenter);
		childCenter.setId("childCenter");
		childCenter.setGridLinesVisible(true);
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
