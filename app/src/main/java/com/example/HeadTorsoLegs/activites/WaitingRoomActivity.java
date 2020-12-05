package com.example.HeadTorsoLegs.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.HeadTorsoLegs.types.MyConstants;
import com.example.HeadTorsoLegs.types.UserData;
import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.example.headtorsolegs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class WaitingRoomActivity extends Activity {

    Button buttonStartGame;
    private FBConnect fbConnect = FBConnect.FBConnect();

    private void updatePlayers(String playerName, int playerNum) {
        if (!playerName.equals(null)) {
            TextView usernameTextView;
            UserData.BodyPart bp = UserData.BodyPart.values()[playerNum];

            switch (bp) {
                case HEAD:
                    usernameTextView = (TextView) findViewById(R.id.textViewHeads);
                    playerName = "Head: " + playerName;
                    break;
                case LEGS:
                    usernameTextView = (TextView) findViewById(R.id.textViewLegs);
                    playerName = "Legs: " + playerName;
                    break;
                default:
                    usernameTextView = (TextView) findViewById(R.id.textViewHeads);
                    break;
            }

            usernameTextView.setText(playerName);
        }
    }

    private void addPlayersListener() {
        // Add listener to the DB
        Log.i("waitingRoom", "Head: " + UserData.BodyPart.HEAD.ordinal() + " Legs: " + UserData.BodyPart.LEGS.ordinal());
        fbConnect.getDBRef().child(fbConnect.subGamePath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.i("waitingRoom", "addPlayersListener: "  + snapshot.getValue().toString());
                if (snapshot.child(UserData.BodyPart.HEAD.getName()).getValue() != null) {
                    if (snapshot.child(UserData.BodyPart.HEAD.getName()).child("playerName").getValue() != null) {
                        updatePlayers(snapshot.child(UserData.BodyPart.HEAD.getName()).child("playerName").getValue().toString(), UserData.BodyPart.HEAD.ordinal());
                    }
                }

                if (snapshot.child(UserData.BodyPart.LEGS.getName()).getValue() != null) {
                    if (snapshot.child(UserData.BodyPart.LEGS.getName()).child("playerName").getValue() != null) {
                        updatePlayers(snapshot.child(UserData.BodyPart.LEGS.getName()).child("playerName").getValue().toString(), UserData.BodyPart.LEGS.ordinal());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("waitingRoom", "onCancelled: " + databaseError.toString());
            }
        });

    }

    private void changeRoomName() {
        Gson gson = new Gson();
        SharedPreferences sharedpreferences = getSharedPreferences(MyConstants.SharedPREFERENCE, Context.MODE_PRIVATE);
        String userDataJson = sharedpreferences.getString(MyConstants.UserDataKEY, "");
        UserData userData = gson.fromJson(userDataJson, UserData.class);
        String gameCode = userData.getGameCode();

        TextView gameTextView = (TextView) findViewById(R.id.textViewGame);
        gameTextView.setText("Game: " + gameCode);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_room);
        buttonStartGame =(Button)findViewById(R.id.buttonStartGame);

        addPlayersListener();
        changeRoomName();

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // todo make this work only if all players are present
                // go to draw screen
                Intent intent = new Intent(view.getContext(), DrawActivity.class);
                view.getContext().startActivity(intent);
            }
            });
    }

}
