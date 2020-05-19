package chatroom.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.Model;
import chatroom.ServiceLocator;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Model extends Model {
	ServiceLocator serviceLocator;

	private final String IP_ADRESS = "147.86.8.31";
	private final int PORTNUMBER = 50001;
	private String securePIN;
	public String currentChatroom;
	public String currentUser;
	public ArrayList<String> newData= new ArrayList<String>();
	public SimpleBooleanProperty successfullAnswer= new SimpleBooleanProperty();
	
	private Socket socket;
	private OutputStreamWriter socketOut;
	private BufferedReader inReader;

	public App_Model() {

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application model initialized");
	}

	// automatic connection
	public boolean connectToServer() {
		boolean connection = false;
		socket = null;

		try {
			socket = new Socket(this.IP_ADRESS, this.PORTNUMBER);
			connection = true;
			serviceLocator.getLogger().info("Application connected to Server " + IP_ADRESS);
			socketOut = new OutputStreamWriter(socket.getOutputStream());
			inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			getTheServerMessages();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return connection;
	}

	private void getTheServerMessages() {
		// TODO Auto-generated method stub

	}

	public boolean createChatroom(String text, boolean selected) {
		// TODO Auto-generated method stub
		return false;
	}

	public void sendAMessage(String messageTxt) {
		Thread thread = new Thread(new Runnable (){

			@Override
			public void run() {
		try {
			socketOut.write("SendMessage|" + securePIN + "|" + currentChatroom + "|" + messageTxt+"\n");
			socketOut.flush();
			Thread.sleep(500);
			ArrayList<String> data = newData;
			if(Boolean.parseBoolean(data.get(1))) {
				serviceLocator.getLogger().info("Succeded to send: " + messageTxt);
				} else {
				serviceLocator.getLogger().info("Failed to send: " + messageTxt);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	
	public boolean joinSelectedChatroom(String chatroom) {
		try {
		socketOut.write("JoinChatroom|" + securePIN + "|" + chatroom+ "|" + currentUser +"\n");
		socketOut.flush();
		Thread.sleep(900);
		if(successfullAnswer.get()&& !(chatroom.equals("null"))) { 
		serviceLocator.getLogger().info("User joined the Chatroom "+ chatroom);
		currentChatroom = chatroom;
		} else {
		serviceLocator.getLogger().info("User failed to join the Chatroom "+ chatroom);
		}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successfullAnswer.get();
	}

}
