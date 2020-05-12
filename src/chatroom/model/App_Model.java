package chatroom.model;

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

	public App_Model() {

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application model initialized");
	}

}
