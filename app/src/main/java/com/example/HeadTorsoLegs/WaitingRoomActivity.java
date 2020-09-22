package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.example.headtorsolegs.R;

public class WaitingRoomActivity extends Activity {
    Button buttonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_room);
        buttonStartGame =(Button)findViewById(R.id.buttonStartGame);

        // this needs to be changed to get data from FB by updating every 10 seconds
        Gson gson = new Gson();
        SharedPreferences sharedpreferences = getSharedPreferences(MyConstants.SharedPREFERENCE, Context.MODE_PRIVATE);
        String userDataJson = sharedpreferences.getString(MyConstants.UserDataKEY, "");
        UserData userData = gson.fromJson(userDataJson, UserData.class);
        displayMessage(userData.getPlayerName());

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
