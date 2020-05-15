package chatroom.view_;



import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.View;
import chatroom.ServiceLocator;
import chatroom.model.App_Model;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
		
	}

	@Override
	protected Scene create_GUI() {
<<<<<<< HEAD
		
=======
>>>>>>> branch 'master' of https://gitlab.fhnw.ch/luca.schaedler/chatroom.git
		return scene;
	}

	
	

	
}