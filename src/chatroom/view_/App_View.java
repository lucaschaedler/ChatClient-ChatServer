package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.View;
import chatroom.ServiceLocator;
import chatroom.model.App_Model;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_View extends View<App_Model> {

	public LoginView loginView;

	public App_View(Stage stage, App_Model model) {
		super(stage, model);
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
		stage.setScene(create_GUI());
		stage.sizeToScene();
		stage.centerOnScreen();
		stage.resizableProperty().setValue(Boolean.FALSE); //no maximizing allowed
		Image msn = new Image(App_View.class.getResourceAsStream("msn_logo.png"));
		stage.getIcons().add(msn);
		stage.setTitle("Chat Messenger");
	}

	@Override
	protected Scene create_GUI() {
		ChatMenu chatMenu = new ChatMenu();
		RoomListView roomListView = new RoomListView();
		ChatScreenView chatScreenView = new ChatScreenView();
		UserListView userListView = new UserListView();
		UserPanel userPanel = new UserPanel();
		BorderPane root = new BorderPane();
		root.setTop(chatMenu);
		root.setLeft(roomListView);
		root.setCenter(chatScreenView);
		root.setRight(userListView);
		root.setBottom(userPanel);
		scene.setRoot(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		return scene;
	}

}