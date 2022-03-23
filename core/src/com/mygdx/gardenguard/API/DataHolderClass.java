package com.mygdx.gardenguard.API;

import java.util.ArrayList;
import java.util.List;

public class DataHolderClass {

    List<Player> players;

    public DataHolderClass() {
        this.players = new ArrayList<>();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }

}
