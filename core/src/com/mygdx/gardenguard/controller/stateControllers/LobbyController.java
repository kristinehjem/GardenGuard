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

    @Override
    public void setPlayers(List<PlayerModel> players) {
        super.setPlayers(players);
        if (super.getPlayers().size() == 5) {
            super.gsm.push(new PlayState());
        }
    }
}
