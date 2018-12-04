package com.example.arthurwang.helloworld.nov;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arthurwang on 2018/11/21
 */
public class Book implements Parcelable {

    public int bookId;

    public String bookName;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    private Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }
}
