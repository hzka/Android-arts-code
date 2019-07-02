package com.example.hzk.myapplication3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 图书信息的类。实现了Parcelable的接口。
 */
public class Book implements Parcelable {
    public int bookId;
    public String bookname;

    protected Book(Parcel in) {
        bookId = in.readInt();
        bookname = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(bookId);
        out.writeString(bookname);
    }
}
