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

public class AndroidInterFaceClass implements FireBaseInterface {
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference gameRef;

    public AndroidInterFaceClass() {
        database = FirebaseDatabase.getInstance();
        gameRef = FirebaseDatabase.getInstance().getReference("games");
        //this is an object in the database
        myRef = database.getReference("message");
    }

    //test function
    @Override
    public void SomeFuction() {
        System.out.println("print in androidinterfaceclass");
    }


    //test function to write to the database
    @Override
    public void FirstFireBaseTest() {
        if (myRef != null) {
            myRef.setValue("Hello, World");
        } else {
            System.out.println("Databaserreference was not set");
        }
    }

    //function to set event listener to different objects in the database
    @Override
    public void SetOnValueChangedListener(final DataHolderClass dataholder) {
        //now we get notified when the object in myRef changes
        myRef.addValueEventListener(new ValueEventListener() {
            //read from the database
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //This method is called once with the initial value and again
                // whenever data at this location is  updated
                String value = snapshot.getValue(String.class);
                dataholder.someValue = value;
                //what you want to do with the changed value
                dataholder.PrintSomeValue();
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public String CreateGameAndPlayer1InDB(Player player) {
        String gamePin = gameRef.push().getKey();
        this.CreatePlayerInDB(gamePin, player);
        return gamePin;
    }

    @Override
    public void CreatePlayerInDB(String gamePin, Player player) {
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
