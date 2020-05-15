package chatroom.view_;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

public class UserListView extends ScrollPane {
	
	public ListView<String> userListView;
	public ObservableList<String> otherUsersOnline = FXCollections.observableArrayList("herbert33", "jakob89", "free_aladin",
			"bastian", "prisitina_gangsta", "player69", "mrr0omantico", "zidane", "dubischdochvumheim22", "gustavo", "chillyvanilly",
			"brudi04", "herpes", "kaspar", "riesen05", "teleskop", "kurva", "guest04", "perle", "hannes", "guest05",
			"johannes", "chatter44", "brudabeforeluda");
	
	public UserListView() {
		userListView = new ListView<>();
		userListView.setItems(otherUsersOnline);
		userListView.setMaxWidth(160);
		this.setContent(userListView);
		this.setPadding(new Insets(10));
		this.setMaxWidth(180);
	}
	

}
