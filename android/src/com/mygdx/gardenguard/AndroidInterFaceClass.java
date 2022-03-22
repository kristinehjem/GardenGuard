package com.mygdx.gardenguard;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AndroidInterFaceClass implements FireBaseInterface{
    FirebaseDatabase database;
    DatabaseReference myRef;

    public AndroidInterFaceClass() {
        database = FirebaseDatabase.getInstance();
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

    //writes value to the target object in the database
    @Override
    public void SetValueInDB(String target, String value) {
        //changing where you want to set the value. Set it to target, and set it to value
        myRef = database.getReference(target);
        myRef.setValue(value);
    }
}
