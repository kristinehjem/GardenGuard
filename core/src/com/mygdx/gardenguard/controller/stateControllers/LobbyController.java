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
        //could be used for testing with only one player:
        /*super.gsm.getFBIC().UpdateGameSwitchInDB(super.gsm.getGamePin(), true);
        super.gsm.set(new PlayState());*/
        if (this.enoughPlayers()) {
            super.gsm.getFBIC().UpdateGameSwitchInDB(super.gsm.getGamePin(), true);
            super.gsm.set(new PlayState());
        } else {
            super.gsm.push(new PopupState("Not enough players \n Please try again \n when you are more players"));
            System.err.println("not enough players");
        }
    }

    public void handleExit() {
        if (super.gsm.getPlayer() instanceof SeekerModel) {
            super.gsm.getFBIC().DeleteGame(super.gsm.getGamePin());
            super.gsm.set(new MenuState());
        }
        else {
            super.gsm.getFBIC().DeletePlayer(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID());
            super.gsm.set(new MenuState());
        }
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
    public void pushNewState() {
        super.gsm.set(new PlayState());
    }
}
