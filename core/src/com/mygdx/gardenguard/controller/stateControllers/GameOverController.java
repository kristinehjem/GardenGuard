package com.mygdx.gardenguard.controller.stateControllers;


import com.badlogic.gdx.Gdx;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.view.MenuState;

import java.util.ArrayList;
import java.util.List;

public class GameOverController extends Controller {

    public GameOverController() {
        super();

    }

    public void handleInput() {
        super.gsm.getFBIC().DeleteGame(super.gsm.getGamePin());
        gsm.set(new MenuState());
    }


    public List<String> getScores() {
        List<PlayerModel> sortedPlayers = new ArrayList<>();
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
            scores.add(player.getPlayerID() + ": " + player.getScore());

        }
        System.out.println(super.getPlayers());
        System.out.println(sortedPlayers);
        return scores;
    }
}
