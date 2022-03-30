package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.State;
import com.mygdx.gardenguard.model.player.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class DataHolderClass {

    GameStateManager gsm;

    public DataHolderClass() {
        this.gsm = GameStateManager.getInstance();
    }

    public void updatePlayers(List<PlayerModel> players){
        State state = GameStateManager.getInstance().getState();
        state.getController().setPlayers(players);
    }
}
