package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.model.player.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class DataHolderClass {

    List<PlayerModel> players;

    public DataHolderClass() {
        this.players = new ArrayList<>();
    }

    public void setPlayers(List<PlayerModel> players) {
        this.players = players;
        for (PlayerModel player : players) {
            System.out.println(player.getPlayerID());
        }
    }
}
