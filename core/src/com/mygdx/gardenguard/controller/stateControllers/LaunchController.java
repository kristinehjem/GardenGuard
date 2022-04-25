package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.view.MenuState;

public class LaunchController extends Controller{

    public LaunchController(){
        super();
    }

    public void handleDone(){
        gsm.push(new MenuState());
    }

}
