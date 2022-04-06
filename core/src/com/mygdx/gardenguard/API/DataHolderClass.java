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
        System.out.println("size of players in dataholderclass: ");
        System.out.println(players.size());
        State state = this.gsm.getState();
        state.getController().setPlayers(players);
    }

    public void updateGameSwitch() {
        State state = this.gsm.getState();
        state.gameSwitchTrue();
    }
}
