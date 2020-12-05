package com.example.HeadTorsoLegs.types;

import android.util.Log;

import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.google.firebase.database.DatabaseReference;


public class UserData {

    private String PlayerName;
    private int PlayerNum;
    private String GameCode;

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

    public void setGameCode(String gameCode) {
        GameCode = gameCode;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public int getPlayerNum() {
        return PlayerNum;
    }

    public String getGameCode() {
        return GameCode;
    }


    public UserData(String name, String gameCode, int playerNum) {
        this.PlayerName = name;
        this.PlayerNum = playerNum;
        this.GameCode = gameCode;
    }

    public void saveNewUserToFB() {
        DatabaseReference DBRef = fbConnect.getDBRef();
        DatabaseReference gameRef = fbConnect.getDBRef().child(fbConnect.subGamePath);
        Log.i("UserData", "setSubGamePath: " + fbConnect.subGamePath);

        if (this.getPlayerNum() == BodyPart.HEAD.ordinal()) {
            Log.i("UserData", "this.getPlayerNum(): "  + this.getPlayerNum());
            Log.i("UserData", "saveNewUserToFB: is head " + this.getPlayerName());
            gameRef.child(BodyPart.HEAD.getName()).setValue(this);
        }
        else if (this.getPlayerNum() == BodyPart.LEGS.ordinal()) {
            Log.i("UserData", "this.getPlayerNum(): "  + this.getPlayerNum());
            Log.i("UserData", "saveNewUserToFB: is legs "+ this.getPlayerName());
            gameRef.child(BodyPart.LEGS.getName()).setValue(this);
        }

        else {
            Log.d("UserData", "saveNewUserToFB: userNum not head or legs");
        }

    }
}