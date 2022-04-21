package com.mygdx.gardenguard.controller.stateControllers;


import com.badlogic.gdx.Gdx;
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.view.MenuState;

import java.util.ArrayList;
import java.util.List;

public class GameOverController extends Controller {

    private List<PlayerModel> sortedPlayers;

    public GameOverController() {
        super();
        this.sortedPlayers = new ArrayList<>();
    }

    public void handleInput() {
        if (super.gsm.getPlayers().size() == 1) {
            super.gsm.getFBIC().DeleteGame(super.gsm.getGamePin());
        }
        else {
            super.gsm.getFBIC().DeletePlayer(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID());
        }
        super.gsm.set(new MenuState());
    }

    public List<String> getScores() {
        for (PlayerModel player : super.getPlayers()) {
            for (int i = 0; i < sortedPlayers.size(); i++) {
                if (player.getScore() > sortedPlayers.get(i).getScore()) {
                    sortedPlayers.add(i, player);
                    break;
                }
            }
            if (!sortedPlayers.contains(player)) {
                sortedPlayers.add(player);
            }
        }
        List<String> scores = new ArrayList<>();
        for (PlayerModel player : sortedPlayers) {
            System.out.println(player.getScore());
            scores.add(player.getUsername() + ": " + player.getScore());
        }
        return scores;
    }

    public List<PlayerModel> getSortedPlayers(){
        return this.sortedPlayers;
    }
}
