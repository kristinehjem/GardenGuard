package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataHolderClass {

    List<Player> players;
    GameStateManager gsm;

    public DataHolderClass(GameStateManager gsm) {
        this.gsm = gsm;
        this.players = new ArrayList<>();
    }

    public void updatePlayers(List<Player> players){
        this.players = players;
        State state = this.gsm.getState();
        state.getController().setPlayers(this.players);
    }
}
