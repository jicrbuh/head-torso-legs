package com.example.HeadTorsoLegs.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.HeadTorsoLegs.types.MyConstants;
import com.example.HeadTorsoLegs.types.UserData;
import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.example.headtorsolegs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class JoinGameActivity extends Activity {
    Button buttonJoin;
    EditText editTextName;
    EditText editGameCode;
    private FBConnect fbConnect = FBConnect.FBConnect();
    SharedPreferences sharedpreferences;
    private View curView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_game);

        sharedpreferences = getSharedPreferences(MyConstants.SharedPREFERENCE, Context.MODE_PRIVATE);
        editTextName = findViewById(R.id.editTextJoinName);
        editGameCode = findViewById(R.id.editTextCode);
        buttonJoin = findViewById(R.id.buttonJoin);
        final UserData userLegs = new UserData("", "", UserData.BodyPart.LEGS.ordinal());
        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curView = view;
                intent = new Intent(view.getContext(), WaitingRoomActivity.class);

                String userName = editTextName.getText().toString();
                String gameCode = editGameCode.getText().toString();

                tryEnterGame(gameCode, userName);

            }
        });
    }

    public void tryEnterGame(final String gameCode, final String userName) {

        final String code = gameCode;
        fbConnect.getDBRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.hasChild(code)) {
                    fbConnect.setSubGamePath(gameCode);

                    UserData userLegs = new UserData(userName, gameCode, UserData.BodyPart.LEGS.ordinal());
                    userLegs.makeLegs();
                    userLegs.saveNewUserToFB();

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    String userDataJson = new Gson().toJson(userLegs);
                    editor.putString(MyConstants.UserDataKEY, userDataJson);
                    editor.commit();

                    // Go to next screen
                    curView.getContext().startActivity(intent);

                }

                else {
                    Log.i("chen", "not snapshot.hasChild(gameCode: " + code);
                    Toast.makeText(getApplicationContext(), "No game found under this Game Code", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("waitingRoom", "onCancelled: " + databaseError.toString());
            }
        });

    }

}
