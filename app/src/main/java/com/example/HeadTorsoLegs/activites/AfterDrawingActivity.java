package com.example.HeadTorsoLegs.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.headtorsolegs.R;

public class AfterDrawingActivity extends Activity {
    Button buttonReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_drawing);
        //todo read from DB if all players finished
        buttonReady = (Button) findViewById(R.id.buttonReady);

        buttonReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowCreationActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
