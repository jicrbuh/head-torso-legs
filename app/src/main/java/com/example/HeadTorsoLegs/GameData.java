package com.example.HeadTorsoLegs;

import android.util.Log;

import java.util.List;

public class GameData {

    //private String PlayerName;
    //private int PlayerNum;
    //private String DrawingPosition;
    //private String GameProgression;
    //private String PushToken;
    private String GameCode;
    private String HeadDrawing;
    private String TorsoDrawing;
    private String LegsDrawing;
    private String GameProgression;
    private String FinalDrawing;
    private List<UserData> Users;

    final private static int CODE_LEN = 4;
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
        return GameCode;
    }
/*
    public String getGameProgression() {
        return GameProgression;
    }

    public String getPushToken() {
        return PushToken;
    }
*/
    public void setGameCode(String gameCode) {
        GameCode = gameCode;
    }

    public void setHeadDrawing(String headDrawing) {
        HeadDrawing = headDrawing;
    }

    public String getHeadDrawing() {
        return this.HeadDrawing;
    }

    public GameData() {
        //this.PlayerName = name;
        //this.PlayerNum = -1;
        this.HeadDrawing = "df";
        this.GameCode = createRandCode();
        //this.PushToken = null;
        //this.GameProgression = null;
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