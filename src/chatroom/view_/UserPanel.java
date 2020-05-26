package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserPanel extends HBox {

	private VBox buttonsBoxLeft = new VBox();
	private VBox buttonsBoxRight = new VBox();
	public Button addRoomBtn, joinRoomBtn, sendMessageBtn, deleteChatroomBtn, leaveRoomBtn;
	public ScrollPane writeScrollPane;
	public TextField writeTextArea;
	public Label currentChatroomLbl;
	
	public UserPanel() {
		addRoomBtn = new Button("Add Room");
		joinRoomBtn = new Button("Join Room");
		sendMessageBtn = new Button("Send");
		deleteChatroomBtn = new Button("Delete Room");
		leaveRoomBtn = new Button("Leave Room");
		writeScrollPane = new ScrollPane();
		writeTextArea = new TextField();
		currentChatroomLbl = new Label();
		
		// MessageBox
		writeTextArea.setText("--->>>Nachrichten schreiben<<<---");
		writeTextArea.setMinWidth(700);
		writeTextArea.setPrefHeight(125);
		//writeTextArea.setMaxHeight(125);
		writeTextArea.setId("writeArea"); // CSS
		writeTextArea.setOnMouseClicked((event) -> {
			writeTextArea.setText("");
		});
		writeScrollPane.setContent(writeTextArea);
		writeScrollPane.setPadding(new Insets(10));
		writeScrollPane.setMinWidth(720);
		writeScrollPane.setMaxHeight(145);

		// BottomBox
		addRoomBtn.setMinSize(100, 60);
		joinRoomBtn.setMinSize(100, 60);
		sendMessageBtn.setMinSize(100, 60);
		buttonsBoxLeft.getChildren().addAll(addRoomBtn, joinRoomBtn, deleteChatroomBtn, leaveRoomBtn, currentChatroomLbl);
		buttonsBoxLeft.setSpacing(5);
		buttonsBoxLeft.setPadding(new Insets(10));
		buttonsBoxRight.getChildren().addAll(sendMessageBtn);
		buttonsBoxRight.setSpacing(5);
		buttonsBoxRight.setPadding(new Insets(10));
		this.getChildren().addAll(buttonsBoxLeft, writeScrollPane, buttonsBoxRight);
		
		updateTexts();
	}

	public String getMessageText() {
		return writeTextArea.getText();
	}
	
	public void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		
		addRoomBtn.setText(t.getString("mainview.button.addroom"));
		joinRoomBtn.setText(t.getString("mainview.button.joinroom"));
		sendMessageBtn.setText(t.getString("mainview.button.sendmessage"));
		deleteChatroomBtn.setText(t.getString("mainview.button.deletechatroom"));
		leaveRoomBtn.setText(t.getString("mainview.button.leavebtn"));
	}

	public void changeChatroomName(String selectedItem) {
		currentChatroomLbl.setText("In Chatroom " + selectedItem);		
	}
}
