package com.example.HeadTorsoLegs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.*;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import android.widget.Toast;
import com.example.headtorsolegs.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.UUID;

public class DrawActivity extends Activity {
    //https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html
    Button buttonFinished;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference("test/");
    StorageReference mountainsRef = storageRef.child("mountains.jpg");

    public String getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
    }


    public void firebaseExample() {
        //final String path = "drawable/flower.bmp";
        final String imageUri = "drawable://" + R.drawable.flower;
        final String path = getURLForResource(R.drawable.flower);
        Uri file = Uri.fromFile(new File(path));

        StorageReference riversRef = storageRef.child("test/"+"hello");
        UploadTask uploadTask = riversRef.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(getApplicationContext(), "Failed Upload: " + path, Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.

            }
        });
    }

    private void uploadView(DrawView drawview) {

        // Get the data from an ImageView as bytes
        drawview.setDrawingCacheEnabled(true);
        drawview.buildDrawingCache();
        Bitmap bitmap = drawview.getCanvasBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_screen);
        buttonFinished = (Button)findViewById(R.id.buttonFinished);
        Log.i("chello!", "before firebase");
        //firebaseExample();

        // go to after drawing room
        buttonFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DrawView drawing = findViewById(R.id.myimageview);
                uploadView(drawing);
                Intent intent = new Intent(view.getContext(), AfterDrawingActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
