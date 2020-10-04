package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.headtorsolegs.R;

public class ShowCreationActivity extends Activity {
    //https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html


    private void changePic() {
        ImageView img= (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.flower);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ImageView img= (ImageView) findViewById(R.id.creation);
                img.setImageResource(R.drawable.flower);
            }
        }, 5000);
    }


}
