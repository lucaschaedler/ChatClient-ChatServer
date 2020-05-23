package chatroom.view_;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

public class UserListScrollPane extends ScrollPane {
	
	public ListView<String> userListView;
	public ObservableList<String> otherUsersOnline = FXCollections.observableArrayList();
	
	public UserListScrollPane() {
		userListView = new ListView<>();
		userListView.setItems(otherUsersOnline);//default
		userListView.setMaxWidth(160);
		this.setContent(userListView);
		this.setPadding(new Insets(10));
		this.setMaxWidth(180);
	}

	public void actualizeChatroomsUsers(ArrayList<String> chatroomUser) {
		userListView = new ListView<String>(FXCollections.observableArrayList(chatroomUser));
		this.setContent(userListView);
	}
	

}
