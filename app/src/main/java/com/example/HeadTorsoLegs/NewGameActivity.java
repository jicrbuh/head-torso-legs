package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.example.headtorsolegs.R;
import com.example.HeadTorsoLegs.MyConstants;

public class NewGameActivity extends Activity {
    SharedPreferences sharedpreferences;
    UserData userData;
    Button buttonCreate;
    EditText editTextName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        sharedpreferences = getSharedPreferences(MyConstants.SharedPREFERENCE, Context.MODE_PRIVATE);

        editTextName = findViewById(R.id.editTextName);
        buttonCreate =(Button)findViewById(R.id.buttonCreate);

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
                // save userData to shared preference -
                // https://stackoverflow.com/questions/7145606/how-do-you-save-store-objects-in-sharedpreferences-on-android
                //how to share it between activities:
                //https://stackoverflow.com/questions/11747687/sharedpreferences-wont-share-between-activities
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
