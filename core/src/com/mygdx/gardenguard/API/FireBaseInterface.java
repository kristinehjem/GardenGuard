package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.controller.stateControllers.MenuController;
import com.mygdx.gardenguard.model.player.PlayerModel;

public interface FireBaseInterface {

    public void SetOnValueChangedListener(DataHolderClass dataholder, String gamePin);

    public String CreateGameInDB();

    public String CreatePlayerInDB(String gamePin, PlayerModel player);

    public void UpdatePositionInDB(String gamePin, String playerID, String value);

    public void UpdateScoreInDB(String target, String value);

    public void checkIfGameExists(String gamePin, MenuController MC);

    void getScores(String gamePin);

    public void DeleteGame(String gamePin);
}
