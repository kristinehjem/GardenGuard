package com.mygdx.gardenguard;

import android.content.Context;

import androidx.annotation.NonNull;

import com.badlogic.gdx.math.Vector2;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.controller.stateControllers.MenuController;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;

import java.util.ArrayList;
import java.util.List;

public class AndroidInterFaceClass implements FireBaseInterface {
    private FirebaseDatabase database;
    private DatabaseReference gameRef;
    private ValueEventListener gameRefListener;
    private ValueEventListener gameSwitchListener;
    private ValueEventListener boardNrListener;
    private ValueEventListener pinExistListener;

    public AndroidInterFaceClass() {
        database = FirebaseDatabase.getInstance();
        //the games in the database
        gameRef = FirebaseDatabase.getInstance().getReference("games");
    }

    //function to set event listener to different objects in the database
    @Override
    public void SetOnValueChangedListener(final DataHolderClass dataholder, String gamePin) {
        //now we get notified when the object in gameRef changes
        this.gameRefListener = new ValueEventListener() {
            //read from the database
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<PlayerModel> players = new ArrayList<>();
                //iterating through all the nodes
                for (DataSnapshot snap : snapshot.child("players").getChildren()) {
                    if ((boolean) snap.child("isSeeker").getValue()) {
                        SeekerModel player = snap.getValue(SeekerModel.class);
                        players.add(player);
                    } else {
                        HiderModel player = snap.getValue(HiderModel.class);
                        players.add(player);
                    }
                }
                dataholder.updatePlayers(players);
            }
            //Log.d(TAG, "Value is: " + value);

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        };
        gameRef.child(gamePin).addValueEventListener(this.gameRefListener);
    }

    //function to set event listener to different objects in the database
    @Override
    public void SetOnGameSwitchChangedListener(final DataHolderClass dataholder, String gamePin) {
        this.gameSwitchListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    if ((boolean) snapshot.getValue() == true) {
                        dataholder.ifTrueSwitch();
                    }
                    else if (!((boolean) snapshot.getValue())){
                        dataholder.ifFalseSwitch();
                    }
            }}
            //Log.d(TAG, "Value is: " + value);
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        };
        gameRef.child(gamePin).child("gameSwitch").addValueEventListener(this.gameSwitchListener);
    }

    @Override
    public void GetBoardNumber(DataHolderClass dataholder, String gamePin) {
        this.boardNrListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    dataholder.updateBoardNr((String) snapshot.getValue());
                }
            }
            //Log.d(TAG, "Value is: " + value);
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        };
        gameRef.child(gamePin).child("boardNr").addValueEventListener(this.boardNrListener);
    }

    public void DeleteGame(String gamePin) {
        gameRef.removeEventListener(this.boardNrListener);
        gameRef.removeEventListener(this.gameSwitchListener);
        gameRef.removeEventListener(this.pinExistListener);
        gameRef.removeEventListener(this.gameRefListener);
        gameRef.child(gamePin).child("gameSwitch").removeValue();
        gameRef.child(gamePin).child("boardNr");
        gameRef.child(gamePin).removeValue();
    }


    public void DeletePlayer(String gamePin, String playerID) {
        gameRef.child(gamePin).child("players").child(playerID).removeValue();
    }


    @Override
    public String CreateGameInDB() {
        String gamePin = gameRef.push().getKey();
        return gamePin;
    }

    @Override
    public String CreatePlayerInDB(String gamePin, PlayerModel player) {
        String playerID = gameRef.push().getKey();
        player.setPlayerID(playerID);
        gameRef.child(gamePin).child("players").child(playerID).setValue(player);
        return playerID;
    }

    @Override
    public void CreateGameSwitchInDB(String gamePin) {
        gameRef.child(gamePin).child("gameSwitch").setValue(false);
    }

    @Override
    public void CreateBoardNumberInDB(String gamePin, String boardNr) {
        gameRef.child(gamePin).child("boardNr").setValue(boardNr);
    }

    @Override
    public void UpdateGameSwitchInDB(String gamePin, boolean gameSwitch) {
        gameRef.child(gamePin).child("gameSwitch").setValue(gameSwitch);
    }

    @Override
    public void UpdatePositionInDB(String gamePin, String playerID, Vector2 position) {
        gameRef.child(gamePin).child("players").child(playerID).child("position").setValue(position);
    }

    @Override
    public void UpdateStepsInDB(String gamePin, String playerID, int value) {
        gameRef.child(gamePin).child("players").child(playerID).child("steps").setValue(value);
    }

    @Override
    public void UpdateScoreInDB(String gamePin, String playerID, int value) {
        gameRef.child(gamePin).child("players").child(playerID).child("score").setValue(value);
    }

    @Override
    public void UpdateUsername(String gamePin, String playerID, String username) {
        gameRef.child(gamePin).child("players").child(playerID).child("username").setValue(username);
    }

    @Override
    public void UpdateIsDoneInDB(String gamePin, String playerID, boolean isDone) {
        gameRef.child(gamePin).child("players").child(playerID).child("isDone").setValue(isDone);
    }

    @Override
    public void UpdateIsFoundInDB(String gamePin, String playerID, boolean value) {
        gameRef.child(gamePin).child("players").child(playerID).child("isFound").setValue(value);
    }

    @Override
    public void checkIfGameExists(String gamePin, MenuController MC) {
        this.pinExistListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.child(gamePin).exists()) {
                    MC.setPinExist(false);
                } else {
                    MC.setPinExist(true);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        gameRef.addListenerForSingleValueEvent(this.pinExistListener);
    }

}
