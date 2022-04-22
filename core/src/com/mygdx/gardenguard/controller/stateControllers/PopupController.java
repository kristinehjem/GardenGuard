package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.view.LobbyState;
import com.mygdx.gardenguard.view.MenuState;
import com.mygdx.gardenguard.view.State;

public class PopupController extends Controller {

    public PopupController(){
        super();
    }

    public void handleClose(){
        if (super.gsm.getPrevState() instanceof LobbyState) {
            super.gsm.set(new LobbyState("username"));
        }
        if (super.gsm.getPrevState() instanceof MenuState) {
            super.gsm.set(new MenuState());
        }
    }
}
