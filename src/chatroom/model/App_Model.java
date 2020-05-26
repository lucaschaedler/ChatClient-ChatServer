package chatroom.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.Model;
import chatroom.ServiceLocator;
import chatroom.view_.InformationView;
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
	private String token;
	public String currentChatroom;
	public String currentUser;
	public ArrayList<String> newData = new ArrayList<String>();
	public SimpleBooleanProperty successfullAnswer = new SimpleBooleanProperty();
	private boolean isOnline = false;
	public ArrayList<String> rooms = new ArrayList<String>();
	private String currentPassword;
	public SimpleStringProperty data = new SimpleStringProperty();
	public SimpleStringProperty message = new SimpleStringProperty();
	public ArrayList<String> chatroomUser = new ArrayList<String>();
	public boolean inChatroom;

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

	public boolean createChatroom(String name, boolean isPublic) {
		try {
			socketOut.write("CreateChatroom|" + token + "|" + name + "|" + isPublic + "\n");
			socketOut.flush();
			Thread.sleep(900);
			if (successfullAnswer.get()) {
				serviceLocator.getLogger().info("User created the Chatroom " + name);
			} else {
				serviceLocator.getLogger().info("User failed to create the Chatroom " + name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successfullAnswer.get();
	}

	public boolean createAccount(String name, String password) {
		boolean createdAccount = false;
		try {
			socketOut.write("CreateLogin|" + name + "|" + password + "\n");
			socketOut.flush();
			Thread.sleep(500);
			if (successfullAnswer.get()) {
				serviceLocator.getLogger().info("User " + name + " created an account.");
			} else {
				serviceLocator.getLogger().info("User: " + name + " failed to create an account.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successfullAnswer.get();
	}

	public boolean login(String name, String password) {

		try {
			socketOut.write("Login|" + name + "|" + password + "\n");
			socketOut.flush();
			Thread.sleep(500);
			ArrayList<String> data = new ArrayList<String>();
			data = newData;
			if (successfullAnswer.get()) {
				isOnline = true;
				currentUser = name;
				token = data.get(3);// token from server

				this.currentPassword = password;
				serviceLocator.getLogger().info("User: " + name + " logged in.");
			} else {
				serviceLocator.getLogger().info("User: " + name + " failed to login.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return successfullAnswer.get();
	}

	public boolean logout(String name, String password) {
		boolean isLoggedOut = false;
		try {
			socketOut.write("Logout|" + name + "|" + password + "\n");
			socketOut.flush();
			Thread.sleep(500);
			if (successfullAnswer.get()) {
				serviceLocator.getLogger().info("User " + name + " is now logged out.");
			} else {
				serviceLocator.getLogger().info("User: " + name + " failed to do logout.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.isOnline = false;
		this.token = null;
		return successfullAnswer.get();
	}

	public void sendMessage(String messageTxt) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					socketOut.write("SendMessage|" + token + "|" + currentChatroom + "|" + messageTxt + "\n");
					socketOut.flush();
					Thread.sleep(500);
					ArrayList<String> data = newData;
					if (Boolean.parseBoolean(data.get(2))) {
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
			socketOut.write("JoinChatroom|" + token + "|" + chatroom + "|" + currentUser + "\n");
			socketOut.flush();
			Thread.sleep(900);
			if (successfullAnswer.get() && !(chatroom.equals("null"))) {
				serviceLocator.getLogger().info("User joined the Chatroom " + chatroom);
				currentChatroom = chatroom;
				inChatroom = true;
			} else {
				serviceLocator.getLogger().info("User failed to join the Chatroom " + chatroom);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successfullAnswer.get();

	}

	public boolean changePassword(String oldPassword, String newPassword) {
		if (oldPassword.equals(currentPassword) && isOnline) {
			try {

				socketOut.write("ChangePassword|" + token + "|" + newPassword + "\n");
				socketOut.flush();
				Thread.sleep(500);
				if (successfullAnswer.get()) {
					serviceLocator.getLogger().info("User changed password");
					currentPassword = newPassword;
				} else {
					serviceLocator.getLogger().info("User failed to change the password");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return successfullAnswer.get();
		} else
			serviceLocator.getLogger().info("OldPassword isn't corret");
		return false;
	}

	public void listChatrooms() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					socketOut.write("ListChatrooms|" + token + "\n");
					socketOut.flush();
					if (successfullAnswer.get()) {

						rooms = newData;
						rooms.remove(0);
						rooms.remove(0);
						rooms.remove(0);
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		);
		thread.setDaemon(true);
		thread.start();

	}

	public static ArrayList<String> extractDataFromMessage(String input) {
		ArrayList<String> data = new ArrayList<String>();
		Scanner scan = new Scanner(input);
		scan.useDelimiter("\\|");
		while (scan.hasNext()) {
			data.add(scan.next());
		}
		return data;
	}

	public void listMessageText(ArrayList<String> data) {
		message.set(data.get(1) + ": " + data.get(data.size() - 1));
	}

	public void getTheServerMessages() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					try {
						data.set(inReader.readLine());
						ArrayList<String> dataAsArray = new ArrayList<String>();
						dataAsArray = extractDataFromMessage(data.get());
						System.out.println(dataAsArray);
						switch (dataAsArray.get(0)) {

						case "MessageText":
							listMessageText(dataAsArray);
							break;
						case "Result":
							if (Boolean.parseBoolean(dataAsArray.get(2))) {
								successfullAnswer.set(true);
								newData = dataAsArray;
							} else {
								successfullAnswer.set(false);
								newData = dataAsArray;
							}
							break;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
		thread.setDaemon(true);
		thread.start();
	}

	public void listTheChatroomUser() {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					socketOut.write("ListChatroomUsers|" + token + "|" + currentChatroom + "|" + currentUser + "\n");
					socketOut.flush();
					if (successfullAnswer.get()) {
						chatroomUser = newData;
						chatroomUser.remove(0);
						chatroomUser.remove(0);
						chatroomUser.remove(0);

					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		);
		thread.setDaemon(true);
		thread.start();
	}

	public boolean deleteAccount(String accountName) {
		try {
			socketOut.write("DeleteLogin|" + token + "|" + accountName + "\n");
			socketOut.flush();
			Thread.sleep(500);
			if (successfullAnswer.get()) {
				serviceLocator.getLogger().info("User deleted the Account " + accountName);
			} else {
				serviceLocator.getLogger().info("User failed to delete the Account " + accountName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successfullAnswer.get();
	}

	public boolean deleteChatroom(String chatroomName) {
		try {
			socketOut.write("DeleteChatroom|" + token + "|" + chatroomName + "\n");
			socketOut.flush();
			Thread.sleep(500);
			if (successfullAnswer.get()) {
				serviceLocator.getLogger().info("User deleted the Chatroom " + chatroomName);
			} else {
				serviceLocator.getLogger().info("User failed to delete the Chatroom " + chatroomName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successfullAnswer.get();
	}

	public boolean leaveChatroom(String chatroomName) {
		try {
			socketOut.write("LeaveChatroom|" + token + "|" + chatroomName + "|" + currentUser + "\n");
			socketOut.flush();
			Thread.sleep(500);
			if (successfullAnswer.get()) {
				inChatroom = false;
				serviceLocator.getLogger().info("User leaved the Chatroom " + chatroomName);
			} else {
				serviceLocator.getLogger().info("User failed to leave the Chatroom " + chatroomName);
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
