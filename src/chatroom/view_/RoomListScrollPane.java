package chatroom.view_;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

public class RoomListScrollPane extends ScrollPane {

	public ListView<String> roomListView;

	public RoomListScrollPane() {
		ArrayList<String> names = new ArrayList<>();
		ObservableList<String> chatRooms = FXCollections.observableArrayList(names);

		roomListView = new ListView<>();
		roomListView.setItems(chatRooms);
		this.setContent(roomListView);
		this.setPadding(new Insets(10));
		this.setPrefWidth(180);
	}

	public void actualizeChatrooms(ArrayList<String> rooms) {
		roomListView = new ListView<String>(FXCollections.observableArrayList(rooms));
		this.setContent(roomListView);

	}

}
