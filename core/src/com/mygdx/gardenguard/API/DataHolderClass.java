package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.GameStateManager;
import com.mygdx.gardenguard.view.State;
import com.mygdx.gardenguard.model.player.PlayerModel;

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

    public void updateGameSwitch() {
        State state = GameStateManager.getInstance().getState();
        state.setTrueSwitch();
    }


    public void ifFalseSwitch() {
        State state = GameStateManager.getInstance().getState();
        state.setFalseSwitch();
    }

    public void updateBoardNr(String boardNr) {
        GameStateManager.getInstance().setBoardNr(Integer.valueOf(boardNr));
    }

}
