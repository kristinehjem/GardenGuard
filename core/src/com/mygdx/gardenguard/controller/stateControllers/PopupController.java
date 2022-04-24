package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.view.LobbyState;
import com.mygdx.gardenguard.view.MenuState;

public class PopupController extends Controller {

    public PopupController(){
        super();
    }

    public void handleClose(){
        if (gsm.getPrevState() instanceof LobbyState) {
            gsm.set(new LobbyState("username"));
        }
        if (gsm.getPrevState() instanceof MenuState) {
            gsm.set(new MenuState());
        }
    }
}
