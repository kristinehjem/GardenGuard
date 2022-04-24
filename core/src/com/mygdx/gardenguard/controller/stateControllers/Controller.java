package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.GameStateManager;
import java.util.List;

public abstract class Controller {

    protected GameStateManager gsm;

    public Controller() {
        this.gsm = GameStateManager.getInstance();
    }

    public PlayerModel getPlayer() {
        return gsm.getPlayer();
    }

    public void setPlayers(List<PlayerModel> players) {
        gsm.setPlayers(players);
    }

    public List<PlayerModel> getPlayers() {
        return gsm.getPlayers();
    }

    public void pushNewState() {

    }

    public int getBoardNr() {
        return gsm.getBoardNr();
    }

}
