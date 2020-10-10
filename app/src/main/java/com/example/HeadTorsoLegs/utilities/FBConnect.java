package com.example.HeadTorsoLegs.utilities;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
//todo make a singleton, return a reference
public class FBConnect {

    private static FBConnect singleInstance = null;
    final private String basePath = "game-data";

    // FB database
    private FirebaseDatabase database;
    private DatabaseReference DBRef;

    //FB storage
    private FirebaseStorage storage;
    private StorageReference storageReference;



    private FBConnect() {
        database = FirebaseDatabase.getInstance();
        DBRef = database.getReference(basePath);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    public DatabaseReference getDBRef() {
        return DBRef;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public StorageReference getStorageReference() {
        return storageReference;
    }

    public static FBConnect FBConnect() {
        if (singleInstance == null)
            singleInstance = new FBConnect();

        return singleInstance;
    }


}

