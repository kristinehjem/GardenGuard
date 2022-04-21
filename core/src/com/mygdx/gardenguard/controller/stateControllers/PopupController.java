package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.view.MenuState;

public class PopupController extends Controller {

    public PopupController(){
        super();
    }

    public void handleClose(){
        super.gsm.pop();
    }
}
