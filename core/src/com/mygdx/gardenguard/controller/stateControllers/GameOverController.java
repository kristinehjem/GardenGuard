package com.mygdx.gardenguard.controller.stateControllers;


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
        if (getPlayers().size() == 1) {
            gsm.getFBIC().DeleteGame(gsm.getGamePin());
        }
        else {
            gsm.getFBIC().DeletePlayer(gsm.getGamePin(), getPlayer().getPlayerID());
        }
        gsm.set(new MenuState());
    }

    public List<String> getScores() {
        for (PlayerModel player : getPlayers()) {
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
            scores.add(player.getUsername() + ": " + player.getScore());
        }
        return scores;
    }

    public List<PlayerModel> getSortedPlayers(){
        return this.sortedPlayers;
    }
}
