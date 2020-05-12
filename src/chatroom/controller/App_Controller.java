package chatroom.controller;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.Controller;
import chatroom.ServiceLocator;
import chatroom.model.App_Model;
import chatroom.view_.App_View;

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
        

       
        
    }
	

}
