package chatroom.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.Model;
import chatroom.ServiceLocator;

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

}
