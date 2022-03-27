package com.mygdx.gardenguard;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.API.Player;
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
        });
    }

    @Override
    public String CreateGameAndPlayer1InDB(PlayerModel player) {
        String gamePin = gameRef.push().getKey();
        this.CreatePlayerInDB(gamePin, player);
        return gamePin;
    }

    @Override
    public void CreatePlayerInDB(String gamePin, PlayerModel player) {
        String playerID = gameRef.push().getKey();
        player.setPlayerID(playerID);
        gameRef.child(gamePin).child("players").child(playerID).setValue(player);
    }

    @Override
    public void UpdatePositionInDB(String gamePin, String playerID, String value) {
        gameRef.child(gamePin).child("players").child(playerID).child("position").setValue(value);
    }

    @Override
    public void UpdateScoreInDB(String target, String value) {

    }
}
