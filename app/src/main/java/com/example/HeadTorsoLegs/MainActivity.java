package com.example.HeadTorsoLegs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.database.*;
import com.example.headtorsolegs.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // https://firebase.google.com/docs/database/android/start#java_1
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    public void goToNewGame(View view) {
        Intent intent = new Intent(MainActivity.this, NewGameActivity.class);
        startActivity(intent);
    }

    public void goToJoinGame(View view) {
        Intent intent = new Intent(MainActivity.this, JoinGameActivity.class);
        startActivity(intent);
    }
}
