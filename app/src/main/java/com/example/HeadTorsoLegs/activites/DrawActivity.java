package com.example.HeadTorsoLegs.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.HeadTorsoLegs.types.MyConstants;
import com.example.HeadTorsoLegs.types.UserData;
import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.example.HeadTorsoLegs.views.DrawView;
import com.example.headtorsolegs.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;


public class DrawActivity extends Activity {
    //https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html
    Button buttonFinished;
    private FBConnect fbConnect = FBConnect.FBConnect();
    StorageReference storageRef = fbConnect.getStorageReference();
    StorageReference drawingRef;


    private void createDrawingRef() {
        Gson gson = new Gson();
        SharedPreferences sharedpreferences = getSharedPreferences(MyConstants.SharedPREFERENCE, Context.MODE_PRIVATE);
        String userDataJson = sharedpreferences.getString(MyConstants.UserDataKEY, "");
        UserData userData = gson.fromJson(userDataJson, UserData.class);
        drawingRef = storageRef.child("drawing_" + userData.getDrawingPosition() + ".jpg");
        // todo move all string consts to MyConstants
    }

    private void uploadDrawView(DrawView drawView) {

        // Get the data from an ImageView as bytes
        drawView.setDrawingCacheEnabled(true);
        drawView.buildDrawingCache();
        Bitmap bitmap = drawView.getCanvasBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = drawingRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(getApplicationContext(), "Failed to Upload: please try again", Toast.LENGTH_LONG).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                //todo maybe intent here
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_screen);
        buttonFinished = (Button)findViewById(R.id.buttonFinished);

        buttonFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DrawView drawing = findViewById(R.id.myimageview);
                createDrawingRef();
                uploadDrawView(drawing);

                // go to after drawing room
                Intent intent = new Intent(view.getContext(), AfterDrawingActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
