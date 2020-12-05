package com.example.HeadTorsoLegs.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.HeadTorsoLegs.types.GameData;
import com.example.HeadTorsoLegs.types.MyConstants;
import com.example.HeadTorsoLegs.types.UserData;
import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.example.headtorsolegs.R;
import com.google.gson.Gson;

public class NewGameActivity extends Activity {
    SharedPreferences sharedpreferences;
    UserData userHead;
    Button buttonCreate;
    EditText editTextName;
    GameData gameData;
    private FBConnect fbConnect = FBConnect.FBConnect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        sharedpreferences = getSharedPreferences(MyConstants.SharedPREFERENCE, Context.MODE_PRIVATE);

        editTextName = findViewById(R.id.editTextName);
        buttonCreate =(Button)findViewById(R.id.buttonCreate);

        // save game data to FB DB
        gameData = new GameData();
        fbConnect.setSubGamePath(gameData.getGameCode());
        gameData.saveGameDataToFB();


        // make button enabled only if the name is not empty
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length() == 0) {
                    buttonCreate.setEnabled(false);
                } else {
                    buttonCreate.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = editTextName.getText().toString();
                userHead = new UserData(userName, gameData.getGameCode(), UserData.BodyPart.HEAD.ordinal());
                userHead.saveNewUserToFB();

                // save userData to sharedPreferences
                SharedPreferences.Editor editor = sharedpreferences.edit(); // todo make this into a singleton
                String userDataJson = new Gson().toJson(userHead);
                editor.putString(MyConstants.UserDataKEY, userDataJson);
                editor.commit();

                // go to waiting room
                Intent intent = new Intent(view.getContext(), WaitingRoomActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }

}
