package com.example.HeadTorsoLegs.types;

import android.util.Log;

import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.google.firebase.database.DatabaseReference;

public class GameData {

    private String gameCode;
    private String headDrawing;
    private String torsoDrawing;
    private String legsDrawing;
    private UserData head;
    private UserData torso;
    private UserData legs;

    final private static int CODE_LEN = 4;
    private static FBConnect fbConnect = FBConnect.FBConnect();



    public String getGameCode() { return gameCode; }

    public void setGameCode(String gameCode) { this.gameCode = gameCode; }

    public void setHeadDrawing(String headDrawing) { this.headDrawing = headDrawing; }

    public String getHeadDrawing() { return this.headDrawing; }

    public void setLegsDrawing(String legsDrawing) { this.legsDrawing = legsDrawing; }

    public String getLegsDrawing() { return this.legsDrawing; }

    public void setTorsoDrawing(String torsoDrawing) { this.torsoDrawing = torsoDrawing; }

    public String getTorsoDrawing() { return this.torsoDrawing; }

    public UserData getHead() { return head; }

    public void setHead(UserData head) { this.head = head; }

    public UserData getTorso() { return torso; }

    public void setTorso(UserData torso) { this.torso = torso; }

    public UserData getLegs() { return legs; }

    public void setLegs(UserData legs) { this.legs = legs; }

    public GameData() {

        this.headDrawing = "";
        this.legsDrawing = "legs_head.jpg";
        this.gameCode = createRandCode();
        this.head = null;

    }

    public void saveGameDataToFB() {
        DatabaseReference DBRef = fbConnect.getDBRef();
        DatabaseReference gameRef = fbConnect.getDBRef().child(fbConnect.subGamePath);
        gameRef.setValue(this);
    }


    // todo library for random?
    private String createRandCode() {
        final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder builder = new StringBuilder();
        int cnt = CODE_LEN;
        while (cnt-- != 0) {
            int charPos = (int) (Math.random()*ALPHA_STRING.length());
            builder.append(ALPHA_STRING.charAt(charPos));
        }
        Log.i("GameCode", "createRandCode: " + builder.toString());
        return builder.toString();
    }


}