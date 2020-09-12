package com.example.HeadTorsoLegs;

public class UserData {

    private String PlayerName;
    private int PlayerNum;
    private String DrawingPosition;
    private String GameCode;
    private String GameProgression;
    private String PushToken;

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


}