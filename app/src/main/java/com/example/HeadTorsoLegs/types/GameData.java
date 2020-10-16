package com.example.HeadTorsoLegs.types;

import android.util.Log;

import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameData {

    private String DrawingPosition;
    private String GameProgression;
    private String PushToken;
    private String gameCode;
    private String headDrawing;
    private String[] myArr;
    private String torsoDrawing;
    private String legsDrawing;
    private String gameProgression;
    private String finalDrawing;
    private UserData head;
    private UserData torso;
    private UserData legs;

    final private static int CODE_LEN = 4;
    private static FBConnect fbConnect = FBConnect.FBConnect();

    public enum BodyPart{
        HEAD("head"),
        TORSO("torso"),
        LEGS("legs");

        private String name;

        BodyPart(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public void setDrawingPosition(String drawingPosition) { DrawingPosition = drawingPosition; }

    public void setGameProgression(String gameProgression) { GameProgression = gameProgression; }

    public void setPushToken(String pushToken) { PushToken = pushToken; }

    public String getDrawingPosition() { return DrawingPosition; }

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
        //this.PlayerName = name;
        //this.PlayerNum = -1;
        this.headDrawing = "df";
        this.legsDrawing = "df";
        this.gameCode = createRandCode();
        this.head = null;

        //this.myArr = new String[]{"0", "1"};
        //this.user = new UserData("standalone");
        //this.PushToken = null;
        //this.GameProgression = null;
    }

    public void saveGameDataToFB() {
        DatabaseReference DBRef = fbConnect.getDBRef();
        DatabaseReference gameRef = fbConnect.getDBRef().child("game2"); // todo all strings to const somewhere
        gameRef.setValue(this);
        saveNewUserToFB();
    }

    public void saveNewUserToFB() {

        DatabaseReference DBRef = fbConnect.getDBRef();
        final DatabaseReference gameRef = fbConnect.getDBRef().child("game2"); // todo all strings to const somewhere
        DatabaseReference usersRef = gameRef.child("head");             // todo all strings to const somewhere
        //Map<String, UserData> usersMap = new HashMap<>();
        //usersMap.put("alanisawesome", new UserData("Alan Turing"));
        UserData lastUser;
        // if no head, save in userData as head
        gameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //parse data to recycler view adapter and call notifyDatasetChange()
                
                if (dataSnapshot.child(BodyPart.HEAD.getName()).getValue() == null) {
                    Log.i("readFB", "dataSnapshot.child(\"head\").getValue(): " + dataSnapshot.child("head").getValue());
                    gameRef.child(BodyPart.HEAD.getName()).setValue(new UserData("Alan Turing"));
                }
                else if (dataSnapshot.child(BodyPart.TORSO.getName()).getValue() == null) {
                    Log.i("readFB", "dataSnapshot.child(\"torso\").getValue(): " + dataSnapshot.child("torso").getValue());
                    gameRef.child(BodyPart.TORSO.getName()).setValue(new UserData("Alan Turing"));
                }
                else if (dataSnapshot.child(BodyPart.LEGS.getName()).getValue() == null) {
                    Log.i("readFB", "dataSnapshot.child(\"legs\").getValue(): " + dataSnapshot.child("legs").getValue());
                    gameRef.child(BodyPart.LEGS.getName()).setValue(new UserData("Alan Turing"));
                }
                else {
                    // error - no slots available for new player
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        //usersRef.setValue(usersMap);
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