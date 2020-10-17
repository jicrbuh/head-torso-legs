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

        BodyPart(String name) { this.name = name; }

        public String getName() { return name; }

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


    public UserData(String name, String gameCode, int playerNum) {
        this.PlayerName = name;
        this.DrawingPosition = null;
        this.PlayerNum = playerNum;
        this.GameCode = gameCode;
        this.PushToken = null;
        this.GameProgression = null;
        saveNewUserToFB();
    }


    public void saveNewUserToFB() {

        DatabaseReference DBRef = fbConnect.getDBRef();
        DatabaseReference gameRef = fbConnect.getDBRef().child("game2"); // todo all strings to const somewhere

        if (this.getPlayerNum() == BodyPart.HEAD.ordinal()) {
            Log.i("chen", "this.getPlayerNum(): "  + this.getPlayerNum());
            Log.i("chen", "saveNewUserToFB: is head " + this.getPlayerName());
            gameRef.child(BodyPart.HEAD.getName()).setValue(this);
        }
        else if (this.getPlayerNum() == BodyPart.LEGS.ordinal()) {
            Log.i("chen", "this.getPlayerNum(): "  + this.getPlayerNum());
            Log.i("chen", "saveNewUserToFB: is legs "+ this.getPlayerName());
            gameRef.child(BodyPart.LEGS.getName()).setValue(this);
        }

    }

    public void makeHead() {
        this.setPlayerNum(0);
        this.setDrawingPosition(BodyPart.HEAD.getName());
    }

    public void makeLegs() {
        this.setPlayerNum(1);
        this.setDrawingPosition(BodyPart.LEGS.getName());
    }
}