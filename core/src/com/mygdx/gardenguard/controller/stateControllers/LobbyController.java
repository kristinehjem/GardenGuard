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
        super.gsm.set(new PlayState());
    }

    public void setUsername(String username) {
        super.gsm.getPlayer().setUsername(username);
        super.gsm.getFBIC().UpdateUsername(super.gsm.getPin(), super.gsm.getPlayer().getPlayerID(), username);
    }
}
