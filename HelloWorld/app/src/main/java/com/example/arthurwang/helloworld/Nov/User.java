package com.example.arthurwang.helloworld.nov;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by arthurwang on 2018/11/21
 */

public class User implements Serializable {


    public int userId;
    public String userName;
    public boolean isMale;

    public Book book;


//    public User(int userId, String userName, boolean isMale) {
//        this.userId = userId;
//        this.userName = userName;
//        this.isMale = isMale;
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(userId);
//        dest.writeString(userName);
//        dest.writeInt(isMale ? 1 : 0);
//        dest.writeParcelable(book, 0);
//    }
//
//    private User(Parcel in) {
//        userId = in.readInt();
//        userName = in.readString();
//        isMale = in.readInt() == 1;
//        book = in.readParcelable(Thread.currentThread().getContextClassLoader());
//    }
//
//
//    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
//        @Override
//        public User createFromParcel(Parcel source) {
//            return new User(source);
//        }
//
//        @Override
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };

    @NonNull
    @Override
    public String toString() {
        return String.format("User:{userId:%s, userName:%s, isMale:%s}", userId, userName, isMale);
    }
}
