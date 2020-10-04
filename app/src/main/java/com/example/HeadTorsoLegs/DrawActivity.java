package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import com.example.headtorsolegs.R;

public class DrawActivity extends Activity {
    //https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html
    Button buttonFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_screen);
        buttonFinished = (Button)findViewById(R.id.buttonFinished);

        // go to after drawing room
        buttonFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AfterDrawingActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
