package com.example.HeadTorsoLegs.activites;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.HeadTorsoLegs.types.UserData;
import com.example.HeadTorsoLegs.utilities.FBConnect;
import com.example.headtorsolegs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import static com.example.HeadTorsoLegs.types.MyConstants.DRAWING;

public class ShowCreationActivity extends Activity {
    //https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html
    private FBConnect fbConnect = FBConnect.FBConnect();

    private void getImgRef(final UserData.BodyPart bodyPart) {

        fbConnect.getDBRef().child(fbConnect.subGamePath).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StorageReference imgRef = null;

                if (dataSnapshot.child((bodyPart.getName() + DRAWING)).getValue() != null) {
                    String pathString = dataSnapshot.child(bodyPart.getName() + DRAWING).getValue().toString();
                    loadImageFB(bodyPart, pathString);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    private void loadImageFB(final UserData.BodyPart bodyPart, String pathString) {
        //old way
        StorageReference storageReference = fbConnect.getStorageReference();
        StorageReference imgReference = storageReference.child(pathString);
        ImageView imageView;
        if (bodyPart == UserData.BodyPart.HEAD) {
            imageView = (ImageView) findViewById(R.id.headCreation);
        }
        else {
            imageView = (ImageView) findViewById(R.id.legsCreation);
        }

        Glide.with(this).load(imgReference).into(imageView);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation);

        getImgRef(UserData.BodyPart.HEAD);
        getImgRef(UserData.BodyPart.LEGS);

    }


}
