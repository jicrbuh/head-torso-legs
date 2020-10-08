package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.gson.Gson;
import com.example.headtorsolegs.R;

import static com.example.HeadTorsoLegs.MyConstants.MAX_NUM_PLAYERS;

public class WaitingRoomActivity extends Activity {

    Button buttonStartGame;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference DBRef = database.getReference("GAME");
    private DatabaseReference mPostReference;

    private void updatePlayers(String playerName, int playerNum) {
        if (!playerName.equals(null) && (playerNum < MAX_NUM_PLAYERS && playerNum > 0)) {
            TextView usernameTextView;

            switch(playerNum) {
                case 1:
                    usernameTextView = (TextView) findViewById(R.id.textViewHeads);
                    playerName = "Heads: " + playerName;
                    break;
                case 2:
                    usernameTextView = (TextView) findViewById(R.id.textViewTorso);
                    playerName = "Torso: " + playerName;
                    break;
                case 3:
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_room);
        buttonStartGame =(Button)findViewById(R.id.buttonStartGame);

        // Add listener to the DB
        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //System.out.println(snapshot.getValue());
                Log.i("waitingRoom", "onDataChange: " + snapshot.getValue());
                Log.i("waitingRoom", "playerName: " + snapshot.child("playerName").getValue().toString());
                updatePlayers(snapshot.child("playerName").getValue().toString(), 1);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //System.out.println("The read failed: " + databaseError.toString());
                Log.i("waitingRoom", "onCancelled: " + databaseError.toString());
            }
        });


        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to draw screen
                Intent intent = new Intent(view.getContext(), DrawActivity.class);
                view.getContext().startActivity(intent);
            }
            });


    }

    public void displayMessage(String message) {
        TextView usernameTextView = (TextView) findViewById(R.id.textViewHeads);
        usernameTextView.setText(message);
    }
}
