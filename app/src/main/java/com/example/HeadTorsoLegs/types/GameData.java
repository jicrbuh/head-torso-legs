package com.example.HeadTorsoLegs.types;

import android.util.Log;

import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameData {

    //private String PlayerName;
    //private int PlayerNum;
    //private String DrawingPosition;
    //private String GameProgression;
    //private String PushToken;
    private String gameCode;
    private String headDrawing;
    private String[] myArr;
    private String torsoDrawing;
    private String legsDrawing;
    private String gameProgression;
    private String finalDrawing;
    //private List<UserData> users;
    //private UserData user;

    final private static int CODE_LEN = 4;
    private static FBConnect fbConnect = FBConnect.FBConnect();
/*
    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public void setPlayerNum(int playerNum) {
        PlayerNum = playerNum;
    }

    public void setDrawingPosition(String drawingPosition) {
        DrawingPosition = drawingPosition;
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
*/
    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public void setHeadDrawing(String headDrawing) {
        this.headDrawing = headDrawing;
    }

    public String getHeadDrawing() {
        return this.headDrawing;
    }

    public void setLegsDrawing(String legsDrawing) {
        this.legsDrawing = legsDrawing;
    }

    public String getLegsDrawing() {
        return this.legsDrawing;
    }

    public void setTorsoDrawing(String torsoDrawing) {
        this.torsoDrawing = torsoDrawing;
    }

    public String getTorsoDrawing() {
        return this.torsoDrawing;
    }

    public String[] getMyArr() { return myArr; }

    public void setMyArr(String[] myArr) { this.myArr = myArr; }

    public GameData() {
        //this.PlayerName = name;
        //this.PlayerNum = -1;
        this.headDrawing = "df";
        this.legsDrawing = "df";
        this.gameCode = createRandCode();

        //this.myArr = new String[]{"0", "1"};
        //this.user = new UserData("standalone");
        //this.PushToken = null;
        //this.GameProgression = null;
    }

    public void saveGameDataToFB() {
        DatabaseReference DBRef = fbConnect.getDBRef();
        DatabaseReference gameRef = fbConnect.getDBRef().child("game2");
        gameRef.setValue(this);
    }


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