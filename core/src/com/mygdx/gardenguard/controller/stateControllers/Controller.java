package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.view.GameStateManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Controller {

    protected List<PlayerModel> players;
    protected GameStateManager gsm;

    public Controller() {
        this.gsm = GameStateManager.getInstance();
        this.players = new ArrayList<>();
    }

    public PlayerModel getPlayer() {
        return gsm.getPlayer();
    }

    public void setPlayers(List<PlayerModel> players) {
        System.out.println("setting players in controller");
        gsm.setPlayers(players);
        //this.players = players;
    }

    public List<PlayerModel> getPlayers() {
        return gsm.getPlayers();
    }
}
