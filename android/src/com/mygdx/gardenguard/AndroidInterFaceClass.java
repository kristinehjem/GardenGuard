package com.mygdx.gardenguard;

import androidx.annotation.NonNull;

import com.badlogic.gdx.math.Vector2;
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
    FirebaseDatabase database;
    DatabaseReference gameRef;

    public AndroidInterFaceClass() {
        database = FirebaseDatabase.getInstance();
        //the games in the database
        gameRef = FirebaseDatabase.getInstance().getReference("games");
    }

    //function to set event listener to different objects in the database
    @Override
    public void SetOnValueChangedListener(final DataHolderClass dataholder, String gamePin) {
        //now we get notified when the object in gameRef changes
        gameRef.child(gamePin).addValueEventListener(new ValueEventListener() {
            //read from the database
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<PlayerModel> players = new ArrayList<>();
                //iterating through all the nodes
                for (DataSnapshot snap : snapshot.child("players").getChildren()) {
                    if (snap.child("isSeeker").getValue() == "true") {
                        System.out.println("er en seeker");
                        SeekerModel player = snap.getValue(SeekerModel.class);
                        players.add(player);
                    } else {
                        System.out.println("er en hider");
                        HiderModel player = snap.getValue(HiderModel.class);
                        players.add(player);
                    }
                }
                for (PlayerModel player: players) {
                    System.out.println(player.getIsSeeker());
                }
                dataholder.updatePlayers(players);
            }
                //Log.d(TAG, "Value is: " + value);

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void DeleteGame(String gamePin) {
        gameRef.child(gamePin).removeValue();
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
    public void UpdatePositionInDB(String gamePin, String playerID, Vector2 position) {
        gameRef.child(gamePin).child("players").child(playerID).child("position").setValue(position);
    }

    @Override
    public void UpdateStepsInDB(String gamePin, String playerID, int value) {
        gameRef.child(gamePin).child("players").child(playerID).child("steps").setValue(value);
    }

    @Override
    public void UpdateUsername(String gamePin, String playerID, String username) {
        gameRef.child(gamePin).child("players").child(playerID).child("username").setValue(username);
    }

    public void UpdateScoreInDB(String gamePin, String playerID, String value) {
        gameRef.child(gamePin).child("players").child(playerID).child("score").setValue(value);
    }

    @Override
    public void UpdateIsFoundInDB(String gamePin, String playerID, String value) {
        gameRef.child(gamePin).child("players").child(playerID).child("isFound").setValue(value);
    }

    @Override
    public void checkIfGameExists(String gamePin, MenuController MC) {
        gameRef.addListenerForSingleValueEvent(new ValueEventListener() {
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
        });
    }

    @Override
    public void getScores(String gamePin) {
        System.out.println(gameRef.child(gamePin).child("players").get());
        //gameRef.child(gamePin).child("players").child(playerID).child("position").setValue(value);

    }
}
