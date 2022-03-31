package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.controller.stateControllers.MenuController;
import com.mygdx.gardenguard.model.player.PlayerModel;

import sun.security.util.ManifestEntryVerifier;

public class CoreInterFaceClass implements FireBaseInterface {

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
    public void UpdateUsername(String gamePin, String playerID, String username) {

    }

    @Override
    public void getScores(String gamePin) {

    }
}
