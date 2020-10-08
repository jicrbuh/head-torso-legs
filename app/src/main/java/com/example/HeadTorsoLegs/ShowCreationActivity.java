package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.*;
import com.example.headtorsolegs.R;

public class ShowCreationActivity extends Activity {
    //https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html


    private void changePic() {
        ImageView img= (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.flower);
    }

    private void loadImageFB() {
        // Reference to an image file in Cloud Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        // ImageView in your Activity
        ImageView imageView = findViewById(R.id.image);

        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)
        Glide.with(this /* context */).load(storageReference).into(imageView);

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
