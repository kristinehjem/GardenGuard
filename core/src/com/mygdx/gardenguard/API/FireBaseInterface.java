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

    public void CreateGameSwitchInDB(String gamePin);

    public void SetOnGameSwitchChangedListener(final DataHolderClass dataholder, String gamePin);

    public void UpdateGameSwitchInDB(String gamePin, boolean gameSwitch);

    public void UpdatePositionInDB(String gamePin, String playerID, Vector2 position);

    public void UpdateStepsInDB(String gamePin, String playerID, int value);

    public void UpdateScoreInDB(String gamePin, String playerID, int value);

    public void UpdateIsFoundInDB(String gamePin, String playerID, boolean value);

    public void checkIfGameExists(String gamePin, MenuController MC);

    public void UpdateUsername(String gamePin, String playerID, String username);

    public void UpdateIsDoneInDB(String gamePin, String playerID, boolean isDone);

    public void getScores(String gamePin);

    public void DeleteGame(String gamePin);

    public void DeletePlayer(String gamePin, String playerID);
}
