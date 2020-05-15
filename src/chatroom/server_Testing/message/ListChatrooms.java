package chatroom.server_Testing.message;

import java.util.ArrayList;

import chatroom.server_Testing.Chatroom;
import chatroom.server_Testing.Client;

public class ListChatrooms extends Message {
	private String token;
	
	public ListChatrooms(String[] data) {
		super(data);
		this.token = data[1];
	}

	@Override
	public void process(Client client) {
		if (client.getToken().equals(token)) {
			ArrayList<String> names = Chatroom.listPublicNames();
			client.send(new Result(this.getClass(), true, names));
		} else {
			client.send(new Result(this.getClass(), false));
		}
	}
}