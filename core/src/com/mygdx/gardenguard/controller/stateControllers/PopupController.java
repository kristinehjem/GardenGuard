package com.mygdx.gardenguard.controller.stateControllers;

public class PopupController extends Controller {

    public PopupController(){
        super();
    }

    public void handleClose(){
        super.gsm.pop();
    }
}
