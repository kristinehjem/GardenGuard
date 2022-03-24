package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.view.GameStateManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Controller {

    protected List<Player> players;
    protected GameStateManager gsm;

    public Controller() {
        this.gsm = GameStateManager.getInstance();
        this.players = new ArrayList<>();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
