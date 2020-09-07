package com.example.HeadTorsoLegs;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {

    private String PlayerName;
    private int Player;
    private String DrawingPosition;
    private String GameCode;
    private String GameProgression;
    private String PushToken;

    public UserData(String name){
        this.PlayerName = name;
        this.Player = -1;
        this.DrawingPosition = null;
        this.GameCode = null;
        this.PushToken = null;
        this.GameProgression = null;
    }


    //parcel part
    public UserData(Parcel in){
        String[] data= new String[6];

    }
    @Override
    public int describeContents() {
// TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
// TODO Auto-generated method stub

    }

    public static final Parcelable.Creator<UserData> CREATOR= new Parcelable.Creator<UserData>() {

        @Override
        public UserData createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new UserData(source);  //using parcelable constructor
        }

        @Override
        public UserData[] newArray(int size) {
// TODO Auto-generated method stub
            return new UserData[size];
        }
    };

}
