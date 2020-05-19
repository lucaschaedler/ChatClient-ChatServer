package chatroom.controller;

import java.util.Optional;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.Controller;
import chatroom.ServiceLocator;
import chatroom.model.App_Model;
import chatroom.view_.App_View;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Controller extends Controller<App_Model, App_View> {
	ServiceLocator serviceLocator;

	public App_Controller(App_Model model, App_View view) {
		super(model, view);

		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
			}
		});

		// test if connection is succeeded
		try {
			if (model.connectToServer()) {
				Alert succededAlert = new Alert(Alert.AlertType.CONFIRMATION);
				succededAlert.setTitle("Connection to server succeeded");
				succededAlert.setContentText("Connection to server succeded");
				Optional<ButtonType> result = succededAlert.showAndWait();

				view.createNewLoginView();

				if (!result.isPresent())
					if (result.get() == ButtonType.CANCEL) {
						view.stop();
					}
			} else {
				Alert unsucceeded = new Alert(Alert.AlertType.WARNING);
				unsucceeded.setTitle("Connection to server failed");
				unsucceeded.setContentText("Connection to server failed");
				Optional<ButtonType> result = unsucceeded.showAndWait();
				if (!result.isPresent())
					if (result.get() == ButtonType.CANCEL) {
						view.stop();
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application controller initialized");

		view.getLoginItem().setOnAction(e -> {
			createLoginView();
		});

		view.getConfirmLoginBtn().setOnAction(e -> {
			doLogin();
		});

		view.getCreateAccountBtn().setOnAction(e -> {
			createAccountView();
		});

		view.getCreateAccountItem().setOnAction(e -> {
			createAccountView();
		});
		
		view.chatMenu.refreshItem.setOnAction(e-> { //evt rausnehmen?
			refresh();
		});
		
		view.chatMenu.exitItem.setOnAction(e -> {
			exit();
		});
		
		view.userPanel.addRoomBtn.setOnAction(e -> {
			addNewRoom();
		});
		
		view.userPanel.joinRoomBtn.setOnAction(e -> {
			joinSelectedRoom();
		});
		
		view.userPanel.blockUserBtn.setOnAction(e -> {
			blockSelectedUser();
		});
		
		view.userPanel.sendMessageBtn.setOnAction(e -> {
			sendMessage();
		});

	}// konstruktor

	private void createAccountView() {
		view.createNewAccountView();
		view.getConfirmCreateAccountBtn().setOnAction(e -> {
			createAccountOnServer();
		});
	}

	private void createLoginView() {
		view.createNewLoginView();
		view.getConfirmLoginBtn().setOnAction(e -> {
			doLogin();
		});
		view.getCreateAccountBtn().setOnAction(e -> {
			createAccountView();
		});
	}

	private void doLogin() {
		// TODO Auto-generated method stub

	}

	private void createAccountOnServer() {
		// TODO Auto-generated method stub

	}
	
	
	private void refresh() {
		// TODO Auto-generated method stub
		
	}



	private void exit() {
		Runtime.getRuntime().exit(0);
	}



	private void addNewRoom() {
		view.createNewChatroomView();
		view.createChatRoomView.createNewRoomBtn.setOnAction(e ->{
			if(model.createChatroom(view.createChatRoomView.chatroomNameTxtF.getText(),
				view.createChatRoomView.isPublicCheckBox.isSelected())) {
				
			}
		});
		
	}



	private void joinSelectedRoom() { 
		// TODO Auto-generated method stub
		
	}



	private void blockSelectedUser() {
		// TODO Auto-generated method stub
		
	}
	
	private void sendMessage() {
		// TODO Auto-generated method stub
		
	}

}// klasse
