package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.model.player.PlayerModel;

public class CoreInterFaceClass implements FireBaseInterface {

    @Override
    public void SetOnValueChangedListener(DataHolderClass dataholder, String gamePin) {

    }

    @Override
    public String CreateGameAndPlayer1InDB(PlayerModel player) {
        return "hei";
    }

    @Override
    public void CreatePlayerInDB(String gamePin, PlayerModel player) {

    }

    @Override
    public void UpdatePositionInDB(String gamePin, String playerID, String value) {

    }

    @Override
    public void UpdateScoreInDB(String target, String value) {

    }

    @Override
    public boolean checkIfGameExists(String gamePin) {
        return false;
    }
}
