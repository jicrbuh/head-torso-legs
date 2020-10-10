package com.example.HeadTorsoLegs.utilities;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
//todo make a singleton, return a reference
public class FBConnect {

    private static FBConnect singleInstance = null;
    final private String basePath = "game-data";
    private FirebaseDatabase database;
    private DatabaseReference DBRef;
    private DatabaseReference mPostReference;


    private FBConnect() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DBRef = database.getReference(basePath);
    }

    public DatabaseReference getDBRef() {
        return DBRef;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public static FBConnect FBConnect() {
        if (singleInstance == null)
            singleInstance = new FBConnect();

        return singleInstance;
    }

    
}

