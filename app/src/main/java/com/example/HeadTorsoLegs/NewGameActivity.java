package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;

import com.example.connectit.R;

import java.util.List;

public class NewGameActivity extends Activity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String UserDataKEY = "userDataKey";
    SharedPreferences sharedpreferences;
    UserData userData;
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        b1=(Button)findViewById(R.id.buttonCreate);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userData = new UserData("test");
                SharedPreferences.Editor editor = sharedpreferences.edit();
                // save userData to shared preference -
                // https://stackoverflow.com/questions/7145606/how-do-you-save-store-objects-in-sharedpreferences-on-android
                String userDataJson = new Gson().toJson(userData);
                editor.putString(UserDataKEY, userDataJson);
                editor.commit();
            }
        });

    }

}
