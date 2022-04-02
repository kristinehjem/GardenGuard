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
    public void CreatePlayerInDB(String gamePin, PlayerModel player) {

    }

    @Override
    public void UpdatePositionInDB(String gamePin, String playerID, String value) {

    }

    @Override
    public void UpdateScoreInDB(String gamePin, String playerID, String value) {

    }

    @Override
    public void UpdateIsFoundInDB(String gamePin, String playerID, String value) {

    }

    @Override
    public void checkIfGameExists(String gamePin, MenuController MC) {

    }
    @Override
    public void getScores(String gamePin) {

    }
}
