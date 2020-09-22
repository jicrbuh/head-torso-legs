package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import com.google.gson.Gson;
import com.example.headtorsolegs.R;
import com.example.HeadTorsoLegs.MyConstants;

public class WaitingRoomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_room);

        // this needs to be changed to get data from FB by updating every 10 seconds
        Gson gson = new Gson();
        SharedPreferences sharedpreferences = getSharedPreferences(MyConstants.SharedPREFERENCE, Context.MODE_PRIVATE);
        String userDataJson = sharedpreferences.getString(MyConstants.UserDataKEY, "");
        UserData userData = gson.fromJson(userDataJson, UserData.class);
        displayMessage(userData.getPlayerName());

    }

    public void displayMessage(String message) {
        TextView usernameTextView = (TextView) findViewById(R.id.textViewHeads);
        usernameTextView.setText(message);
    }
}
