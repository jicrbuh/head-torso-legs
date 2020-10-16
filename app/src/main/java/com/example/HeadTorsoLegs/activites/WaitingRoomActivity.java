package com.example.HeadTorsoLegs.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.example.headtorsolegs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.HeadTorsoLegs.types.MyConstants.MAX_NUM_PLAYERS;

public class WaitingRoomActivity extends Activity {

    Button buttonStartGame;
    private FBConnect fbConnect = FBConnect.FBConnect();

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
        fbConnect.getDBRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                updatePlayers(snapshot.child("readUser").child("playerName").getValue().toString(), 1);
                //todo move playerName to MyConstants
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
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

    //todo delete
    public void displayMessage(String message) {
        TextView usernameTextView = (TextView) findViewById(R.id.textViewHeads);
        usernameTextView.setText(message);
    }
}
