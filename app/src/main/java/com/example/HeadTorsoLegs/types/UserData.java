package com.example.HeadTorsoLegs.types;

import android.util.Log;

import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedOutputStream;


public class UserData {

    private String PlayerName;
    private int PlayerNum;
    private String DrawingPosition;
    private String GameCode;
    private String GameProgression;
    private String PushToken;

    private static FBConnect fbConnect = FBConnect.FBConnect();

    public enum BodyPart {
        HEAD("head"),
        TORSO("torso"),
        LEGS("legs");

        private String name;
        private int val;

        BodyPart(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        private BodyPart(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public void setPlayerNum(int playerNum) {
        PlayerNum = playerNum;
    }

    public void setDrawingPosition(String drawingPosition) {
        DrawingPosition = drawingPosition;
    }

    public void setGameCode(String gameCode) {
        GameCode = gameCode;
    }

    public void setGameProgression(String gameProgression) {
        GameProgression = gameProgression;
    }

    public void setPushToken(String pushToken) {
        PushToken = pushToken;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public int getPlayerNum() {
        return PlayerNum;
    }

    public String getDrawingPosition() {
        return DrawingPosition;
    }

    public String getGameCode() {
        return GameCode;
    }

    public String getGameProgression() {
        return GameProgression;
    }

    public String getPushToken() {
        return PushToken;
    }


    public UserData(String name) {
        this.PlayerName = name;
        this.PlayerNum = -1;
        this.DrawingPosition = null;
        this.GameCode = null;
        this.PushToken = null;
        this.GameProgression = null;
    }


    public void saveNewUserToFB() {

        DatabaseReference DBRef = fbConnect.getDBRef();
        final DatabaseReference gameRef = fbConnect.getDBRef().child("game2"); // todo all strings to const somewhere

        // if no head, save in userData as head
        gameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                setGameCode(dataSnapshot.child("gameCode").getValue().toString());

                if (dataSnapshot.child(BodyPart.HEAD.getName()).getValue() == null) {
                    Log.i("readFB", "dataSnapshot.child(\"head\").getValue(): " + dataSnapshot.child("head").getValue());
                    UserData.this.setPlayerNum(BodyPart.HEAD.getVal());
                    gameRef.child(BodyPart.HEAD.getName()).setValue(UserData.this);

                } else if (dataSnapshot.child(BodyPart.TORSO.getName()).getValue() == null) {
                    Log.i("readFB", "dataSnapshot.child(\"torso\").getValue(): " + dataSnapshot.child("torso").getValue());
                    UserData.this.setPlayerNum(BodyPart.TORSO.getVal());
                    gameRef.child(BodyPart.TORSO.getName()).setValue(UserData.this);

                } else if (dataSnapshot.child(BodyPart.LEGS.getName()).getValue() == null) {
                    Log.i("readFB", "dataSnapshot.child(\"legs\").getValue(): " + dataSnapshot.child("legs").getValue());
                    UserData.this.setPlayerNum(BodyPart.LEGS.getVal());
                    gameRef.child(BodyPart.LEGS.getName()).setValue(UserData.this);

                } else {
                    // error - no slots available for new player
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}