package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.View;
import chatroom.ServiceLocator;
import chatroom.model.App_Model;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_View extends View<App_Model> {

	// classes
	public LoginView loginView;
	public AccountView accountView;
	public ChatMenu chatMenu;
	public RoomListScrollPane roomListScrollPane;
	public ChatScreenView chatScreenView;
	public UserListScrollPane userListScrollPane;
	public UserPanel userPanel;
	public BorderPane root;
	public CreateNewChatroomView createNewChatroomView;
	public MyProfileView myProfileView;
	public ChangePasswordView changePasswordView;
	public BlockListView blockListView;

	public App_View(Stage stage, App_Model model) {
		super(stage, model);
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
		stage.setScene(create_GUI());
		stage.sizeToScene();
		stage.centerOnScreen();
		stage.resizableProperty().setValue(Boolean.FALSE); // no maximizing allowed
		Image msn = new Image(App_View.class.getResourceAsStream("msn_logo.png"));
		stage.getIcons().add(msn);
		stage.setTitle("Chat Messenger");
	}

	@Override
	protected Scene create_GUI() {
		chatMenu = new ChatMenu();
		roomListScrollPane = new RoomListScrollPane();
		chatScreenView = new ChatScreenView();
		userListScrollPane = new UserListScrollPane();
		userPanel = new UserPanel();
		root = new BorderPane();
		root.setTop(chatMenu);
		root.setLeft(roomListScrollPane);
		root.setCenter(chatScreenView);
		root.setRight(userListScrollPane);
		root.setBottom(userPanel);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("mainview.css").toExternalForm());
		return scene;
	}

	public void createNewLoginView() {
		loginView = new LoginView();
	}

	public void createNewLoginViewStart() {
		loginView.start();
	}

	public void createNewLoginViewStop() {
		loginView.stop();
	}

	public Button getConfirmLoginBtn() {
		return loginView.getConfirmBtn();
	}

	public Button getCreateAccountBtn() {
		return loginView.getCreateAccountBtn();
	}

	public void createNewAccountView() {
		accountView = new AccountView();
	}

	public void createNewAccountViewStart() {
		accountView.start();
	}

	public void createNewAccountViewStop() {
		accountView.stop();
	}

	public MenuItem getCreateAccountItem() {
		return chatMenu.getCreateAccountItem();
	}

	public MenuItem getLoginItem() {
		return chatMenu.getLoginItem();
	}

	public Button getConfirmCreateAccountBtn() {
		return accountView.getConfirmCreateAccountBtn();
	}

	public void createChatroomView() {
		createNewChatroomView = new CreateNewChatroomView();
	}

	public void createMyProfilView() {
		myProfileView = new MyProfileView();
	}

	public void createChangePasswordView() {
		changePasswordView = new ChangePasswordView();
	}

	public void createBlockListView() {
		blockListView = new BlockListView();
	}

	public void createNewChatroomView() {
		createNewChatroomView = new CreateNewChatroomView();

	}

	public String getUsernameCreate() {
		return accountView.getUserName();
	}

	public String getPasswordCreate() {
		return accountView.getPassword();
	}

	public String getLoginPassword() {
		return loginView.getPassword();
	}

	public String getLoginUsername() {
		return loginView.getUserName();
	}

	public void showUser(String name) {
		chatMenu.showUser(name);
	}

	public void destroyLoginView() {
		loginView.getLoginFrame().close();
	}

	public Button getCancelBtnAccountView() {
		return accountView.getCancelBtn();
	}

	public MenuItem getLogoutItem() {
		return chatMenu.getLogoutItem();
	}


}