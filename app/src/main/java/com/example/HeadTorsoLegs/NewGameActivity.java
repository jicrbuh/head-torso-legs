package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.*;
import com.google.gson.Gson;
import com.example.headtorsolegs.R;

public class NewGameActivity extends Activity {
    SharedPreferences sharedpreferences;
    UserData userData;
    Button buttonCreate;
    EditText editTextName;
    GameData gameData;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference DBRef = database.getReference("game-data");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        sharedpreferences = getSharedPreferences(MyConstants.SharedPREFERENCE, Context.MODE_PRIVATE);

        editTextName = findViewById(R.id.editTextName);
        buttonCreate =(Button)findViewById(R.id.buttonCreate);

        gameData = new GameData();

        DatabaseReference gameRef = DBRef.child("game2");
        Log.i("gameData", "onCreate: headdrawing - " + gameData.getHeadDrawing());
        gameRef.setValue(gameData);



        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    buttonCreate.setEnabled(false);
                } else {
                    buttonCreate.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });


        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = editTextName.getText().toString();
                userData = new UserData(userName);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                String userDataJson = new Gson().toJson(userData);
                editor.putString(MyConstants.UserDataKEY, userDataJson);
                editor.commit();

                // push to FB
                FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = dataBase.getReference("GAME");
                myRef.setValue(userData);

                // go to waiting room
                Intent intent = new Intent(view.getContext(), WaitingRoomActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }

}
