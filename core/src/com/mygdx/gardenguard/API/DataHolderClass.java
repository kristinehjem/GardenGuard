package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.State;
import com.mygdx.gardenguard.model.player.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class DataHolderClass {

    List<PlayerModel> players;
    GameStateManager gsm;

    public DataHolderClass() {
        this.gsm = GameStateManager.getInstance();
        this.players = new ArrayList<>();
    }

    public void updatePlayers(List<PlayerModel> players){
        this.players = players;
        State state = this.gsm.getState();
        state.getController().setPlayers(this.players);
    }
}
