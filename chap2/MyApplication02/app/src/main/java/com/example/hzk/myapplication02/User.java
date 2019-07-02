package com.example.hzk.myapplication02;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 功能：Parcelable、Serializable。
 */

public class User implements Parcelable{
    public int userId;
    public String userName;
    public boolean isMale;
//    public Person person;
    protected User(int userId,String userName,boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale  = isMale;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(Parcel in) {
        userId = in.readInt();
        userName =in.readString();
        isMale = in.readInt() ==1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(userId);
        out.writeString(userName);
        out.writeInt(isMale?1:0);
//        out.writeParcelable((Parcelable) person,0);
    }
}
