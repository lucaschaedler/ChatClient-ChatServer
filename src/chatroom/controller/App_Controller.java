package chatroom.controller;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.Controller;
import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import chatroom.model.App_Model;
import chatroom.server.Account;
import chatroom.view_.App_View;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
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
				doLogout();
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
				view.createNewLoginViewStart();

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

		model.message.addListener((observable, oldValue, newValue) -> {
			Platform.runLater(() -> {
				view.chatScreenView.createNewMessageLine(newValue);
				listChatroomUser();
			});
		});

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

		view.chatMenu.myProfileItem.setOnAction(e -> {
			createMyProfilView();
		});

		view.chatMenu.changePasswordItem.setOnAction(e -> {
			createChangePasswordView();
		});

		view.chatMenu.getLogoutItem().setOnAction(e -> {
			doLogout();
		});

		view.chatMenu.deleteAccountItem.setOnAction(e -> {
			createDeleteAccountView();
		});

		view.chatMenu.refreshItem.setOnAction(e -> { // evt rausnehmen?
			refresh();
		});

		view.chatMenu.exitItem.setOnAction(e -> {
			exit();
		});

		view.userPanel.addRoomBtn.setOnAction(e -> {
			addNewRoom();
		});

		view.userPanel.joinRoomBtn.setOnAction(e -> {
			joinChatroom();
		});

		view.userPanel.sendMessageBtn.setOnAction(e -> {
			sendMessage();
		});

		view.userPanel.writeTextArea.setOnAction(e -> {
			sendMessage();

		});

		view.userPanel.deleteRoomBtn.setOnAction(e -> {
			deleteChatroom();
		});

		view.userPanel.leaveRoomBtn.setOnAction(e -> {
			leaveChatroom();
		});

		view.chatMenu.userOnlineItem.setOnAction(e -> {
			createIsUserOnlineView();
		});

		ServiceLocator sl = ServiceLocator.getServiceLocator();
		for (Locale locale : sl.getLocales()) {
			MenuItem language = new MenuItem(locale.getLanguage());
			view.chatMenu.languageMenu.getItems().add(language);
			language.setOnAction(event -> {
				sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
				sl.setTranslator(new Translator(locale.getLanguage()));
				try {
					view.chatMenu.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}
				try {
					view.accountView.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}
				try {
					view.changePasswordView.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}
				try {
					view.loginView.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}
				try {
					view.myProfileView.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}
				try {
					view.userPanel.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}
				try {
					view.createNewChatroomView.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}
				try {
					view.info.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}
				try {
					view.isUserOnline.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}
				try {
					view.deleteAccountView.updateTexts();
				} catch (NullPointerException e) {
					// ignore happen when view isn't open
				}

				view.getStage().sizeToScene();

			});
		}

	}// konstruktor

	private void createDeleteAccountView() {
		view.createDeleteAccountView();
		view.deleteAccountView.closeBtn.setOnAction(e -> {
			view.deleteAccountView.stop();
		});
		view.deleteAccountView.confirmBtn.setOnAction(e -> {
			deleteAccount();
		});
	}

	private void createIsUserOnlineView() {
		view.createIsUserOnlineView();
		view.isUserOnline.confirmOnlineBtn.setOnAction(e -> {
			isUserOnline();
		});
	}

	private void isUserOnline() {
		if (model.isUserOnline(view.isUserOnline.userField.getText())) {
			view.isUserOnline.removeResult();
			view.isUserOnline.userIsOnline();
		} else {
			view.isUserOnline.removeResult();
			view.isUserOnline.userIsOffline();
		}
	}

	private void closeView() {
		view.myProfileView.stop();
	}

	private void createChangePasswordView() {
		view.createChangePasswordView();
		view.changePasswordView.getConfirmBtn().setOnAction(e -> {
			changePassword();
		});
	}

	private void createMyProfilView() {
		view.createMyProfilView();
		view.myProfileView.usernameLbl2.setText(model.getUserName());
		if (model.getIsOnline()) {
			view.myProfileView.statusLbl2.setText("Online");
		} else {
			view.myProfileView.statusLbl2.setText("Offline");
		}
		view.myProfileView.closeBtn.setOnAction(e -> {
			closeView();
		});
	}

	private void createAccountView() {
		view.createNewAccountView();
		view.createNewAccountViewStart();
		try {
			view.createNewLoginViewStop();
		} catch (Exception e) {
			// falls keine Login offen ist ignorieren
		}
		view.getConfirmCreateAccountBtn().setOnAction(e -> {
			createAccountOnServer();
		});
		view.getCancelBtnAccountView().setOnAction(e -> {
			view.createNewLoginViewStart();
			view.createNewAccountViewStop();
		});
	}

	private void createLoginView() {
		view.createNewLoginView();
		view.createNewLoginViewStart();
		view.getConfirmLoginBtn().setOnAction(e -> {
			doLogin();
		});
		view.getCreateAccountBtn().setOnAction(e -> {
			createAccountView();
		});
	}

	private void exit() {
		
		serviceLocator.getConfiguration().save();
		doLogout();
		Platform.exit();
		System.exit(0);
		serviceLocator.getLogger().info("Application terminated");

	}

	private void addNewRoom() {
		view.createChatroomView();
		view.createNewChatroomView.createNewRoomBtn.setOnAction(e -> {
			if (model.createChatroom(view.createNewChatroomView.chatroomNameTxtF.getText())) {
				view.createNewChatroomView.stop();
				listChatrooms();
			} else {
				view.createNewChatroomView.failedToCreateChatroom();
			}
		});

	}

	private void listChatrooms() {
		for (int i = 0; i < 2; i++) {
			model.listChatrooms();
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			view.roomListScrollPane.actualizeChatrooms(model.rooms);

		}
	}

	private void joinChatroom() {
		if (model.currentChatroom != null) {
			leaveChatroomDelete();
		}
		if (view.roomListScrollPane.roomListView.getSelectionModel().getSelectedItem() != null) {
			model.joinSelectedChatroom(view.roomListScrollPane.roomListView.getSelectionModel().getSelectedItem());
			view.userPanel
					.changeChatroomName(view.roomListScrollPane.roomListView.getSelectionModel().getSelectedItem());
			view.chatScreenView.removeAllMessages();
			listChatroomUser();
		}
	}

	private void listChatroomUser() {
		for (int i = 0; i < 2; i++) {
			model.listTheChatroomUser();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			view.userListScrollPane.actualizeChatroomsUsers(model.chatroomUser);
		}
	}

	private void sendMessage() {
		if (model.inChatroom) {
			model.sendMessage(view.userPanel.writeTextArea.getText());
			view.userPanel.writeTextArea.clear();
			listChatroomUser();
		} else {
			view.createInformationView();
			view.info.start();
		}
	}

	private void changePassword() {
		if (model.changePassword(view.changePasswordView.oldPassword.getText(),
				view.changePasswordView.newPassword.getText())) {
			view.changePasswordView.close();
		} else {
			view.changePasswordView.failedToChangePassword();
		}
	}

	private void doLogout() {
		if (model.currentChatroom != null) {
			leaveChatroomDelete();
		}
		String name = view.getLoginUsername();
		String password = view.getLoginPassword();
		model.logout(name, password);
		view.stop();
		view.createNewLoginViewStart();
		view.getLogoutItem().setDisable(true);
		view.getLoginItem().setDisable(false);
		view.getCreateAccountItem().setDisable(false);
	}

	private void deleteAccount() {
		String name = view.loginView.getUserName();
		Account acc = chatroom.server.Account.exists(name);
		chatroom.server.Account.remove(acc);
		if (chatroom.server.Account.exists(name) == null) {
			model.deleteAccount(name);
			this.doLogout();
		} else {

		}
	}

	private void refresh() {
		listChatrooms();
	}

	private void doLogin() {
		String name = view.getLoginUsername();
		String password = view.getLoginPassword();
		if (model.login(name, password)) {
			view.showUser(name);
			view.start();// main gui start when login is correct
			view.destroyLoginView();
			view.getLoginItem().setDisable(true);
			view.getLogoutItem().setDisable(false);
			view.getCreateAccountItem().setDisable(true);
			listChatrooms();// liste der chatrooms
			view.loginView.successfullLogin();

		} else {
			view.loginView.failedToDoLogin();

		}
	}

	private void createAccountOnServer() {

		if (view.accountView.password.getText().equals(view.accountView.passwordConfirm.getText())) {
			String name = view.getUsernameCreate();
			String password = view.getPasswordCreate();
			if (model.createAccount(name, password)) {
				view.createNewAccountViewStop();
				view.createNewLoginViewStart();
			} else {
				view.accountView.failedToCreateAccount();
			}
		} else {
			view.accountView.failedPassword();
		}

	}

	public void deleteChatroom() {
		if (Objects.equals(model.currentChatroom, view.roomListScrollPane.roomListView.getSelectionModel().getSelectedItem())) {
			leaveChatroomDelete();
		}
		if (model.deleteChatroom(view.roomListScrollPane.roomListView.getSelectionModel().getSelectedItem())) {
			listChatrooms();
		}

	}

	private void leaveChatroomDelete() {
		if (model.leaveChatroom(model.currentChatroom)) {
			view.userPanel.changeChatroomName("--");
			view.userPanel.writeTextArea.clear();
			view.chatScreenView.removeAllMessages();
			view.userListScrollPane.emptyList();
		}
	}

	private void leaveChatroom() {
		if (model.leaveChatroom(view.roomListScrollPane.roomListView.getSelectionModel().getSelectedItem())) {
			view.userPanel.changeChatroomName("--");
			view.userPanel.writeTextArea.clear();
			view.chatScreenView.removeAllMessages();
			view.userListScrollPane.emptyList();
			listChatrooms();
		}
	}

}// klasse
