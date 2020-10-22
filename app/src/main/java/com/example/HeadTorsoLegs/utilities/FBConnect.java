package com.example.HeadTorsoLegs.utilities;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
public class FBConnect {

    private static FBConnect singleInstance = null;
    final static private String baseDBPath = "game-data";
    final static private String baseStoragePath = "test";
    static public String subGamePath = "game2";

    // FB database
    private FirebaseDatabase database;
    private DatabaseReference DBRef;

    //FB storage
    private FirebaseStorage storage;
    private StorageReference storageReference;


    public void setSubGamePath(String gamePath) {

        if (!gamePath.equals("")) {
            //Log.i("chenconnect", "setSubGamePath: " + gamePath);
            this.subGamePath = gamePath;
        }

    }

    private FBConnect() {
        database = FirebaseDatabase.getInstance();
        DBRef = database.getReference(baseDBPath);
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

