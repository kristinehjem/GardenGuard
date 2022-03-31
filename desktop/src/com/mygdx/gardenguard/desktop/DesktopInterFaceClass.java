package com.mygdx.gardenguard.desktop;

import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.controller.stateControllers.MenuController;
import com.mygdx.gardenguard.model.player.PlayerModel;

public class DesktopInterFaceClass implements FireBaseInterface {

    @Override
    public void SetOnValueChangedListener(DataHolderClass dataholder, String gamePin) {
    }

    @Override
    public String CreateGameInDB() {
        return "hei";
    }

    @Override
    public String CreatePlayerInDB(String gamePin, PlayerModel player) {
        return "hei";
    }

    @Override
    public void UpdatePositionInDB(String gamePin, String playerID, String value) {

    }

    @Override
    public void UpdateScoreInDB(String target, String value) {

    }

    @Override
    public void checkIfGameExists(String gamePin, MenuController MC) {

    }


    @Override
    public void getScores(String gamePin) {

    }

    @Override
    public void DeleteGame(String gamePin) {

    }
}
