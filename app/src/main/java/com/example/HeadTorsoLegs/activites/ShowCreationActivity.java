package com.example.HeadTorsoLegs.activites;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.example.headtorsolegs.R;
import com.google.firebase.storage.StorageReference;

public class ShowCreationActivity extends Activity {
    //https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html
    private FBConnect fbConnect = FBConnect.FBConnect();

    private void changePic() {
        ImageView img= (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.flower);
    }


    private void loadImageFB() {

        // Reference to an image file in Cloud Storage
        StorageReference storageReference = fbConnect.getStorageReference();
        StorageReference imgReference = storageReference.child("test/mountains.jpg");
        // ImageView in your Activity
        ImageView imageView = (ImageView) findViewById(R.id.creation);

        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)
        Glide.with(this).load(imgReference).into(imageView);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ImageView img= (ImageView) findViewById(R.id.creation);
                loadImageFB();
                //img.setImageResource(R.drawable.flower);
            }
        }, 1000);

    }


}
