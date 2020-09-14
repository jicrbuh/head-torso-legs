package com.example.HeadTorsoLegs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.headtorsolegs.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
