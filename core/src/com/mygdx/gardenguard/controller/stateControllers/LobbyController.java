package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.PlayState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LobbyController extends Controller {

    public LobbyController() {
        super();
    }

    public void handleStart() {
        super.gsm.set(new PlayState()); //fjern denne. brukes bare for testing
        /*if (this.enoughPlayers()) {
            super.gsm.set(new PlayState());
        } else {
            System.err.println("not enough players");
        }*/
    }

    private boolean enoughPlayers() {
        if (super.getPlayers().size() > 1) {
            return true;
        } return false;
    }
}
