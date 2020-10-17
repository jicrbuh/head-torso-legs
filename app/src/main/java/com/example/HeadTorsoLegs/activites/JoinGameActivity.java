package com.example.HeadTorsoLegs.activites;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.HeadTorsoLegs.types.MyConstants;
import com.example.HeadTorsoLegs.types.UserData;
import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.example.headtorsolegs.R;
import com.google.gson.Gson;

public class JoinGameActivity extends Activity {
    Button buttonJoin;
    EditText editTextName;
    private FBConnect fbConnect = FBConnect.FBConnect();
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_game);


        editTextName = findViewById(R.id.editTextName);
        buttonJoin =(Button)findViewById(R.id.buttonJoin);

        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = editTextName.getText().toString();
                UserData userData = new UserData(userName, "", UserData.BodyPart.LEGS.ordinal());
                userData.makeLegs();

                // push to FB - fix all to the singleton
                //todo maybe can get rid of sharedprefrence
                fbConnect.getDBRef().child("readUser").setValue(userData);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                String userDataJson = new Gson().toJson(userData);
                editor.putString(MyConstants.UserDataKEY, userDataJson);
                editor.commit();

                // go to waiting room
                Intent intent = new Intent(view.getContext(), WaitingRoomActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
