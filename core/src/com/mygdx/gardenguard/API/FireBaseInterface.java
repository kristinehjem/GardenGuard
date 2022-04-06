package com.mygdx.gardenguard.API;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.controller.stateControllers.MenuController;
import com.mygdx.gardenguard.model.player.PlayerModel;

public interface FireBaseInterface {

    public void SetOnValueChangedListener(DataHolderClass dataholder, String gamePin);

    public String CreateGameInDB();

    public String CreatePlayerInDB(String gamePin, PlayerModel player);

    public void UpdatePositionInDB(String gamePin, String playerID, Vector2 position);

    public void UpdateStepsInDB(String gamePin, String playerID, int value);

    public void UpdateScoreInDB(String gamePin, String playerID, String value);

    public void UpdateIsFoundInDB(String gamePin, String playerID, String value);

    public void checkIfGameExists(String gamePin, MenuController MC);

    public void UpdateUsername(String gamePin, String playerID, String username);

    void getScores(String gamePin);

    public void DeleteGame(String gamePin);
}
