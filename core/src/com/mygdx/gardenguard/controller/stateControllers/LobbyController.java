package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.MenuState;
import com.mygdx.gardenguard.view.PlayState;
import com.mygdx.gardenguard.view.PopupState;

public class LobbyController extends Controller {

    public LobbyController() {
        super();
    }

    public void handleStart() {
        if (this.enoughPlayers()) {
            gsm.getFBIC().UpdateGameSwitchInDB(gsm.getGamePin(), true);
            gsm.set(new PlayState());
        } else {
            super.gsm.push(new PopupState("Not enough players \n Please try again \n when you are more players"));
        }
    }

    public void handleExit() {
        if (getPlayer() instanceof SeekerModel) {
            gsm.getFBIC().DeleteGame(gsm.getGamePin());
            gsm.set(new MenuState());
        }
        else {
            gsm.getFBIC().DeletePlayer(gsm.getGamePin(), gsm.getPlayer().getPlayerID());
            gsm.set(new MenuState());
        }
    }

    private boolean enoughPlayers() {
        if (getPlayers().size() > 1) {
            return true;
        } return false;
    }

    public void setUsername(String username) {
        getPlayer().setUsername(username);
        gsm.getFBIC().UpdateUsername(gsm.getGamePin(), getPlayer().getPlayerID(), username);
    }

    public String getPin() {
        return gsm.getGamePin();
    }

    @Override
    public void pushNewState() {
        gsm.set(new PlayState());
    }
}
