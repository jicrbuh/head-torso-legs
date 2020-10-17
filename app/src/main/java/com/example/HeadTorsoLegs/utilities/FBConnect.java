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
    final private String baseDBPath = "game-data";
    final private String baseStoragePath = "test";

    // FB database
    private FirebaseDatabase database;
    private DatabaseReference DBRef;

    //FB storage
    private FirebaseStorage storage;
    private StorageReference storageReference;



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

    public void readData(final FBCallback myCallback) {
        DatabaseReference temp = this.getDBRef().child("game2").child("head");
        this.getDBRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("chen", "readData: dataSnapshot.child(\"playerName\").getValue(): " + dataSnapshot.child("playerName").getValue());
                String value = dataSnapshot.child("playerName").getValue().toString();
                myCallback.onCallback(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}

