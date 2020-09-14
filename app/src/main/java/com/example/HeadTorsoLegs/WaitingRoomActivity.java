package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import com.google.gson.Gson;
import com.example.headtorsolegs.R;

public class WaitingRoomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_room);

        Gson gson = new Gson();
        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String userDataJson = sharedpreferences.getString("userDataKey", "");
        UserData userData = gson.fromJson(userDataJson, UserData.class);
        displayMessage(userData.getPlayerName());
    }

    public void displayMessage(String message) {
        TextView usernameTextView = (TextView) findViewById(R.id.TextViewHeads);
        usernameTextView.setText(message);
    }
}
