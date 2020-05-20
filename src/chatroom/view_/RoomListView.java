package chatroom.view_;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

public class RoomListView extends ScrollPane {

	public ListView<String> roomListView;
	public ObservableList<String> chatRooms = FXCollections.observableArrayList("gayroom", "playaroom", "girlsroom",
			"shippinoroom");

	public RoomListView(){
		roomListView = new ListView<>();
		roomListView.setItems(chatRooms);
		roomListView.setMaxWidth(160);
		this.setContent(roomListView);
		this.setPadding(new Insets(10));
		this.setMaxWidth(180);
	}

	public void actualizeChatrooms(ArrayList<String> rooms) {
		// TODO Auto-generated method stub
		
	}
}
