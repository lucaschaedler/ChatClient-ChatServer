package chatroom.controller;

import java.util.Locale;
import java.util.Optional;
import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.Controller;
import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import chatroom.model.App_Model;
import chatroom.view_.App_View;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
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

		view.chatMenu.myProfileItem.setOnAction(e -> {
			createMyProfilView();
		});

		view.chatMenu.changePasswordItem.setOnAction(e -> {
			createChangePasswordView();
		});

		view.chatMenu.getMenuLogoutItem().setOnAction(e -> {
			doLogout();
		});

		view.chatMenu.blockListItem.setOnAction(e -> {
			createBlockListView();
		});

		view.chatMenu.deleteAccountItem.setOnAction(e -> {
			deleteAccount();
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
					view.blockListView.updateTexts();
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

			});
		}

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
			joinSelectedRoom();
		});

		view.userPanel.blockUserBtn.setOnAction(e -> {
			blockSelectedUser();
		});

		view.userPanel.sendMessageBtn.setOnAction(e -> {
			sendMessage();
		});

	}// konstruktor

	private void createBlockListView() {
		view.createBlockListView();
		view.blockListView.getUnblockBtn().setOnAction(e -> {
			unBlockUser();
		});
	}

	private void createChangePasswordView() {
		view.createChangePasswordView();
		view.changePasswordView.getConfirmBtn().setOnAction(e -> {
			changePassword();
		});
	}

	private void createMyProfilView() {
		view.createMyProfilView();
	}

	private void createAccountView() {
		view.createNewAccountView();
		view.start(); // test nacher empfernen
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
			//hide wenn neue account erstelle
			//verschwindet wenn man erfolgreich erstellt hat
		});
	}

	private void exit() {
		serviceLocator.getConfiguration().save();
		Platform.exit();
		System.exit(0);
		serviceLocator.getLogger().info("Application terminated");

	}

	private void addNewRoom() {
		view.createChatroomView();
		view.createChatRoomView.createNewRoomBtn.setOnAction(e -> {
			if (model.createChatroom(view.createChatRoomView.chatroomNameTxtF.getText(),
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

	private void changePassword() {
		// TODO Auto-generated method stub
	}

	private void doLogout() {
		String name = view.getLoginUsername();
		String password = view.getLoginPassword();
		model.logout(name, password);
			view.stop();
		
	}

	private void unBlockUser() {
		// TODO Auto-generated method stub

	}

	private void deleteAccount() {
		// TODO Auto-generated method stub

	}

	private void doLogin() {
		String name = view.getLoginUsername();
		String password = view.getLoginPassword();
		if (model.login(name, password)) {
			view.showUser(name);
			view.start();//startet das gui wenn eingeloggt
			view.destroyLoginView();
			
			

		} else {
			view.loginView.failedToDoLogin();

		}
	}

	private void createAccountOnServer() {
		String name = view.getUsernameCreate();
		String password = view.getPasswordCreate();
		if (model.createAccount(name, password)) {

		} else {
			view.accountView.failedToCreateAccount();
		}
	}

	private void refresh() {
		// TODO Auto-generated method stub

	}

}// klasse
