package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateNewChatroomView extends Stage {

	public Button createNewRoomBtn;
	public TextField chatroomNameTxtF;
	public CheckBox isPublicCheckBox;
	private GridPane root;
	public Label chatroomNameLbl;
	public Label isPublicLbl;
	
	public CreateNewChatroomView(){
		root = new GridPane();
		chatroomNameLbl = new Label("Chatroom Name");
		isPublicLbl = new Label ("Public Room");
		chatroomNameTxtF = new TextField();
		isPublicCheckBox = new CheckBox();
		createNewRoomBtn = new Button("Create Room");
		
		root.add(chatroomNameLbl, 0,0);
		root.add(chatroomNameTxtF, 1,0);
		root.add(isPublicLbl, 0, 1);
		root.add(isPublicCheckBox, 1,1);
		root.add(createNewRoomBtn, 0,2);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("mainview.css").toExternalForm());
		this.setScene(scene);
		this.resizableProperty().setValue(Boolean.FALSE);
		this.show();
		this.setAlwaysOnTop(true);
		
	}
	
	public void failedToCreateChatroom() {
		Label failedLbl = new Label ("Failed to create Chatroom");
		failedLbl.setTextFill(Color.RED);
		root.add(failedLbl, 0, 3);
		this.sizeToScene();
	}
	
	 public void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator(); 
	       
	        // The menu entries here
	       chatroomNameLbl.setText(t.getString("button.menu.createChatroomFrame.chatroomNameLbl"));
	       isPublicLbl.setText(t.getString("button.menu.createChatroomFrame.isPublicLbl"));
	       createNewRoomBtn.setText(t.getString("button.menu.createChatroomFrame.createnewroombtn"));
	       
	       
	    }
}