package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.PlayState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LobbyController extends Controller {

    public LobbyController() {
        super();
    }

    public void handleStart() {
        super.gsm.getFBIC().UpdateGameSwitchInDB(super.gsm.getGamePin(), true);
        super.gsm.set(new PlayState());
        /*if (this.enoughPlayers()) {
            super.gsm.getFBIC().UpdateGameSwitchInDB(super.gsm.getGamePin(), true);
            super.gsm.set(new PlayState());
        } else {
            System.err.println("not enough players");
        }*/
    }

    private boolean enoughPlayers() {
        if (super.getPlayers().size() > 1) {
            return true;
        } return false;
    }

    public void setUsername(String username) {
        super.gsm.getPlayer().setUsername(username);
        super.gsm.getFBIC().UpdateUsername(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), username);
    }

    public String getPin() {
        return super.gsm.getGamePin();
    }

    @Override
    public void setGameSwitch(boolean gameSwitch) {
        super.gameSwitch = gameSwitch;
        if (super.getGameSwitch() == true) {
            super.gsm.set(new PlayState());
        }
    }
}
